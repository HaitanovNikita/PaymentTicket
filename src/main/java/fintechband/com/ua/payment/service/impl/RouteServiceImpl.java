package fintechband.com.ua.payment.service.impl;

import fintechband.com.ua.payment.data.converter.Converter;
import fintechband.com.ua.payment.data.dao.RouteDao;
import fintechband.com.ua.payment.data.dto.RouteDTO;
import fintechband.com.ua.payment.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static fintechband.com.ua.payment.data.converter.Converter.convertDtoToEntity;
import static fintechband.com.ua.payment.data.converter.Converter.convertEntityToDto;

/**
 * @author nhaitanov
 */
@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteDao routeDao;

    @Override
    public List<RouteDTO> findAll() {
        return routeDao
                .findAll()
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RouteDTO findById(Long id) {
        return convertEntityToDto(routeDao.findById(id));
    }

    @Override
    public RouteDTO save(RouteDTO routeDTO) {
        return convertEntityToDto(routeDao.save(convertDtoToEntity(routeDTO)));
    }

    @Override
    public void deleteById(Long id) {
        routeDao.deleteById(id);
    }
}
