package fintechband.com.ua.payment.web.controller;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.data.dto.StatusDTO;
import fintechband.com.ua.payment.service.PaymentGatewayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author nhaitanov
 */
@Slf4j
@RestController
@RequestMapping("/payment-gateway")
@Api(value = "Endpoint to manage payment gateway",
        description = "Operations pertaining to payment gateway lifecycle")
public class PaymentGatewayController {

    @Autowired
    private PaymentGatewayService paymentGatewayService;

    @PostMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get status of application")
    public ResponseEntity<StatusDTO> getStatusOfApplication(@RequestBody ApplicationDTO applicationDTO) {
        log.debug("Get status of application by application [id:{}]",applicationDTO.getId());
        return new ResponseEntity<>(paymentGatewayService.getStatusOfApplication(applicationDTO), HttpStatus.OK);
    }
}
