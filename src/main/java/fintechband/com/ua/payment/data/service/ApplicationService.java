package fintechband.com.ua.payment.data.service;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;

import java.util.List;

/**
 * @author nhaitanov
 */
public interface ApplicationService {

    List<ApplicationDTO> findAll();

    ApplicationDTO findById(Long id);

    List<ApplicationDTO> findByStatus(Long status);

    ApplicationDTO save(ApplicationDTO applicationDTO);

    void deleteById(Long id);
}
