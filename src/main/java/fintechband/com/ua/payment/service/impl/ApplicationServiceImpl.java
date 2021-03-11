package fintechband.com.ua.payment.service.impl;

import fintechband.com.ua.payment.data.converter.Converter;
import fintechband.com.ua.payment.data.dao.ApplicationDao;
import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.service.ApplicationService;
import fintechband.com.ua.payment.validation.Marshalling;
import fintechband.com.ua.payment.validation.Unmarshalling;
import fintechband.com.ua.payment.validation.model.application.ApplicationRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

import static fintechband.com.ua.payment.data.converter.Converter.*;

/**
 * @author nhaitanov
 */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    @Qualifier("marshallingForApplicationResponse")
    private Marshaller marshaller;

    @Autowired
    private Marshalling marshalling;

    @Autowired
    @Qualifier("unmarshallingForApplicationRequest")
    private Unmarshaller unmarshaller;

    @Autowired
    private Unmarshalling unmarshalling;


    @Override
    public List<String> findAll() {
        return applicationDao
                .findAll()
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList())
                .stream().map(apl -> {
                    try {
                        return marshalling.marshalling(convertDtoToModel(apl), marshaller);
                    } catch (ValidationException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public String findById(Long id) {
        return marshalling.marshalling(convertDtoToModel(convertEntityToDto(applicationDao.findById(id))), marshaller);
    }

    @Override
    public List<String> findAllByStatus(Long status) {
        return applicationDao
                .findAllByStatus(status)
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList())
                .stream().map(apl -> {
                    try {
                        return marshalling.marshalling(convertDtoToModel(apl), marshaller);
                    } catch (ValidationException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public String findByStatus(Long status) {
        return marshalling.marshalling(convertDtoToModel(convertEntityToDto(applicationDao.findByStatus(status))), marshaller);
    }

    @SneakyThrows
    @Override
    public Long save(String xmlApplicationRequest) {
        ApplicationRequest applicationRequest = unmarshalling.validate(ApplicationRequest.class, unmarshaller, xmlApplicationRequest);
        return applicationDao.save(convertDtoToEntity(convertModelToDto(applicationRequest)));
    }

    @SneakyThrows
    @Override
    public Long update(String xmlApplicationRequest) {
        ApplicationRequest applicationRequest = unmarshalling.validate(ApplicationRequest.class, unmarshaller, xmlApplicationRequest);
        return applicationDao.update(convertDtoToEntity(convertModelToDto(applicationRequest)));
    }

    @Override
    public void deleteById(Long id) {
        applicationDao.deleteById(id);
    }
}
