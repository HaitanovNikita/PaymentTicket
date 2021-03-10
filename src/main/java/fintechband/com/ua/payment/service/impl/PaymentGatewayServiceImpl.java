package fintechband.com.ua.payment.service.impl;

import fintechband.com.ua.payment.data.dao.StatusDao;
import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.data.dto.StatusDTO;
import fintechband.com.ua.payment.service.PaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static fintechband.com.ua.payment.data.converter.Converter.convertEntityToDto;

/**
 * @author nhaitanov
 */
@Service
@Transactional
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    @Autowired
    private StatusDao statusDao;

    @Override
    public StatusDTO getStatusOfApplication(ApplicationDTO applicationDTO) {
        return convertEntityToDto(statusDao.findById(getRandomNumberOfStatus(1, 3)));
    }

    private Long getRandomNumberOfStatus(int min, int max) {
        max -= min;
        return Long.valueOf((long) (Math.random() * ++max) + min);
    }

}
