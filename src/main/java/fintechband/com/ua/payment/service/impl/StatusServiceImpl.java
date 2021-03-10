package fintechband.com.ua.payment.service.impl;

import fintechband.com.ua.payment.data.converter.Converter;
import fintechband.com.ua.payment.data.dao.StatusDao;
import fintechband.com.ua.payment.data.dto.StatusDTO;
import fintechband.com.ua.payment.service.StatusService;
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
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusDao statusDao;

    @Override
    public List<StatusDTO> findAll() {
        return statusDao
                .findAll()
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StatusDTO findById(Long id) {
        return convertEntityToDto(statusDao.findById(id));
    }

    @Override
    public StatusDTO save(StatusDTO statusDTO) {
        return convertEntityToDto(statusDao.save(convertDtoToEntity(statusDTO)));
    }

    @Override
    public void deleteById(Long id) {
        statusDao.deleteById(id);
    }
}
