package fintechband.com.ua.payment.data.service;

import fintechband.com.ua.payment.data.dto.StatusDTO;

import java.util.List;

/**
 * @author nhaitanov
 */
public interface StatusService {
    List<StatusDTO> findAll();

    StatusDTO findById(Long id);

    StatusDTO save(StatusDTO statusDTO);

    void deleteById(Long id);
}
