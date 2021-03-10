package fintechband.com.ua.payment.service;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.data.dto.StatusDTO;

/**
 * @author nhaitanov
 */
public interface PaymentGatewayService {

    StatusDTO getStatusOfApplication(ApplicationDTO applicationDTO);
}
