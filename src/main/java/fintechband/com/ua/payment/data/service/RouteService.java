package fintechband.com.ua.payment.data.service;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.data.dto.RouteDTO;

import java.util.List;

/**
 * @author nhaitanov
 */
public interface RouteService {
    List<RouteDTO> findAll();

    RouteDTO findById(Long id);

    RouteDTO save(RouteDTO RouteDTO);

    void deleteById(Long id);

}
