package fintechband.com.ua.payment.service.impl;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.data.dto.StatusDTO;
import fintechband.com.ua.payment.service.ProcessingApplicationProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static fintechband.com.ua.payment.utils.PaymentConstants.ERROR_STATUS_CODE_APPLICATION;
import static fintechband.com.ua.payment.utils.PaymentConstants.POSTED_STATUS_CODE_APPLICATION;

/**
 * @author nhaitanov
 */
@Service
@Transactional
public class ProcessingApplicationProcessServiceImpl implements ProcessingApplicationProcessService {


    private final RestTemplate restTemplate;
    @Value("${application.url-path.host}")
    private String host;
    @Value("${application.url-path.status-service}")
    private String urlStatusService;
    @Value("${application.url-path.payment-service}")
    private String urlPaymentService;
    @Value("${application.url-path.application-service}")
    private String urlApplicationService;

    @Autowired
    public ProcessingApplicationProcessServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ApplicationDTO> allApplicationsProcessing() {
        List<ApplicationDTO> listApplicationsForProcessing = restTemplate
                .exchange(host + urlApplicationService, HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<ApplicationDTO>>() {
                })
                .getBody();
        return listApplicationsForProcessing
                .stream()
                .filter(apl -> apl.getStatus() != ERROR_STATUS_CODE_APPLICATION && apl.getStatus() != POSTED_STATUS_CODE_APPLICATION)
                .map(apl -> performOperationProcessingApplication(apl))
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDTO applicationProcessing(Long id) {
        String urlForGetApplicationForProcessing = host + urlApplicationService + "/find/status/" + id;
        ApplicationDTO applicationDTOForProcessing;
        if ((applicationDTOForProcessing = restTemplate.exchange(urlForGetApplicationForProcessing, HttpMethod.GET, null, ApplicationDTO.class).getBody()) != null) {
            return performOperationProcessingApplication(applicationDTOForProcessing);
        }
        throw new IllegalArgumentException(String.format("An unexpected error occurred while processing a ticket with ID: %s", id));
    }

    private ApplicationDTO performOperationProcessingApplication(ApplicationDTO applicationDTOForProcessing) {
        if (applicationDTOForProcessing.getStatus() != ERROR_STATUS_CODE_APPLICATION
                && applicationDTOForProcessing.getStatus() != POSTED_STATUS_CODE_APPLICATION) {

            String urlForGetStatusDtoOfApplication = host + urlPaymentService + "/status";
            StatusDTO statusDTO;

            if ((statusDTO = restTemplate.exchange(urlForGetStatusDtoOfApplication, HttpMethod.POST, new HttpEntity<>(applicationDTOForProcessing), StatusDTO.class).getBody()) != null) {
                applicationDTOForProcessing.setStatus(statusDTO.getId());
                String urlForUpdateApplicationDTOAfterProcessing = host + urlApplicationService + "/update";
                Long idApplicationDtoAfterProcessing;
                if ((idApplicationDtoAfterProcessing = restTemplate.exchange(urlForUpdateApplicationDTOAfterProcessing, HttpMethod.POST, new HttpEntity<>(applicationDTOForProcessing), Long.class).getBody()) != null) {
                    if (applicationDTOForProcessing.getId() == idApplicationDtoAfterProcessing) {
                        return applicationDTOForProcessing;
                    }
                }
            }
        }
        return null;
    }
}
