package fintechband.com.ua.payment.service;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;

import java.util.List;

/**
 * @author nhaitanov
 */
public interface ProcessingApplicationProcessService {

    List<ApplicationDTO> allApplicationsProcessing();
    ApplicationDTO applicationProcessing (Long id);

}
