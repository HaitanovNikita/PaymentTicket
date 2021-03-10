package fintechband.com.ua.payment.data.service.impl;

import fintechband.com.ua.payment.data.converter.Converter;
import fintechband.com.ua.payment.data.dao.ApplicationDao;
import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.data.service.ApplicationService;
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
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public List<ApplicationDTO> findAll() {
        return applicationDao
                .findAll()
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDTO findById(Long id) {
        return convertEntityToDto(applicationDao.findById(id));
    }

    @Override
    public List<ApplicationDTO> findByStatus(Long status) {
        return applicationDao
                .findByStatus(status)
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(ApplicationDTO applicationDTO) {
        return applicationDao.save(convertDtoToEntity(applicationDTO));
    }

    @Override
    public void deleteById(Long id) {
        applicationDao.deleteById(id);
    }
}
