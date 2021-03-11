package fintechband.com.ua.payment.service.impl;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.data.dto.StatusDTO;
import fintechband.com.ua.payment.service.ProcessingApplicationProcessService;
import fintechband.com.ua.payment.validation.Unmarshalling;
import fintechband.com.ua.payment.validation.model.application.ApplicationRequest;
import fintechband.com.ua.payment.validation.model.application.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static fintechband.com.ua.payment.data.converter.Converter.convertDtoToXmlRequest;
import static fintechband.com.ua.payment.data.converter.Converter.convertModelToDto;
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
    @Value("${application.url-path.payment-service}")
    private String urlPaymentService;
    @Value("${application.url-path.application-service}")
    private String urlApplicationService;

    @Autowired
    @Qualifier("unmarshallingForApplicationResponse")
    private Unmarshaller unmarshaller;

    @Autowired
    private Unmarshalling unmarshalling;

    @Autowired
    public ProcessingApplicationProcessServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ApplicationDTO> allApplicationsProcessing() {

        ArrayList<String> appliactionsArrayList = restTemplate
                .exchange(host + urlApplicationService, HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<String>>() {
                })
                .getBody();

        List<ApplicationResponse> applicationResponsesAfterValidate = appliactionsArrayList//Get all applications in xml(List<String>)
                .stream()
                .map(apl -> {
                    try {
                        return unmarshalling.validate(ApplicationResponse.class, unmarshaller, apl);//Map List<String> to List<Application(<Model)>
                    } catch (ValidationException e) {
                        System.err.println(e.getMessage());
                        return null;
                    }
                })
                .collect(Collectors.toList());

        List<ApplicationDTO> listApplicationsForProcessing = applicationResponsesAfterValidate
                .stream()
                .filter(apl -> apl != null)
                .map(apl -> convertModelToDto(apl))//Map List<Application(Model)> to List<ApplicationDto>
                .collect(Collectors.toList());

        return listApplicationsForProcessing
                .stream()
                .filter(apl ->
                        apl != null
                                && apl.getStatus() != ERROR_STATUS_CODE_APPLICATION
                                && apl.getStatus() != POSTED_STATUS_CODE_APPLICATION)
                .map(apl -> performOperationProcessingApplication(apl))
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDTO applicationProcessing(Long id) {
        String urlForGetApplicationForProcessing = host + urlApplicationService + "/find/status/" + id;
        try {
            ResponseEntity<String> applicationDTOResponseEntity = restTemplate.exchange(urlForGetApplicationForProcessing, HttpMethod.GET, null, String.class);
            ApplicationDTO applicationDTOForProcessing = convertModelToDto(unmarshalling.validate(ApplicationRequest.class, unmarshaller, applicationDTOResponseEntity.getBody()));
            if (applicationDTOForProcessing != null) {
                return performOperationProcessingApplication(applicationDTOForProcessing);
            }
        } catch (Exception exception) {
            throw new IllegalArgumentException(String.format("An unexpected error occurred while processing a ticket with ID: %s details exception: %s", id, exception.toString()));
        }
        throw new IllegalArgumentException(String.format("An unexpected error occurred while processing a ticket with ID: %s", id));
    }

    private ApplicationDTO performOperationProcessingApplication(ApplicationDTO applicationDTOForProcessing) {
        if (applicationDTOForProcessing != null
                && applicationDTOForProcessing.getStatus() != ERROR_STATUS_CODE_APPLICATION
                && applicationDTOForProcessing.getStatus() != POSTED_STATUS_CODE_APPLICATION) {

            String urlForGetStatusDtoOfApplication = host + urlPaymentService + "/status";
            StatusDTO statusDTO;

            if ((statusDTO = restTemplate.exchange(urlForGetStatusDtoOfApplication, HttpMethod.POST, new HttpEntity<>(applicationDTOForProcessing), StatusDTO.class).getBody()) != null) {
                applicationDTOForProcessing.setStatus(statusDTO.getId());

                String urlForUpdateApplicationDTOAfterProcessing = host + urlApplicationService + "/update";
                Long idApplicationDtoAfterProcessing;
                String xml = convertDtoToXmlRequest(applicationDTOForProcessing);

                if ((idApplicationDtoAfterProcessing = restTemplate.exchange(urlForUpdateApplicationDTOAfterProcessing, HttpMethod.POST, new HttpEntity<>(xml, getHttpHeaders()), Long.class).getBody()) != null) {
                    if (applicationDTOForProcessing.getId() == idApplicationDtoAfterProcessing) {
                        return applicationDTOForProcessing;
                    }
                }
            }
        }

        throw new IllegalArgumentException(String.format("An unexpected error occurred while processing a ticket with ID: %s", applicationDTOForProcessing.getId()));
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON));
        return headers;
    }
}
