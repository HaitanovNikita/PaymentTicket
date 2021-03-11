package fintechband.com.ua.payment.service;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;

import java.util.List;

/**
 * @author nhaitanov
 */
public interface ApplicationService {

    List<String> findAll();

    String findById(Long id);

    List<String> findAllByStatus(Long status);

    String findByStatus(Long status);

    Long save(String xmlApplicationRequest);

    Long update(String xmlApplicationRequest);

    void deleteById(Long id);
}
