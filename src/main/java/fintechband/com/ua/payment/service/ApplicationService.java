package fintechband.com.ua.payment.service;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;

import java.util.List;

/**
 * @author nhaitanov
 */
public interface ApplicationService {

    List<ApplicationDTO> findAll();

    ApplicationDTO findById(Long id);

    List<ApplicationDTO> findAllByStatus(Long status);

    ApplicationDTO findByStatus(Long status);

    Long save(ApplicationDTO applicationDTO);

    Long update(ApplicationDTO applicationDTO);

    void deleteById(Long id);
}
