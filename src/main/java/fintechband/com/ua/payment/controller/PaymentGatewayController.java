package fintechband.com.ua.payment.controller;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.data.dto.StatusDTO;
import fintechband.com.ua.payment.service.PaymentGatewayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nhaitanov
 */
@RestController
@RequestMapping("/payment-gateway/")
@Api(value = "Endpoint to manage payment gateway",
        description = "Operations pertaining to payment gateway lifecycle")
public class PaymentGatewayController {

    @Autowired
    private PaymentGatewayService paymentGatewayService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get status of application")
    public ResponseEntity<StatusDTO> getStatusOfApplication(@RequestBody ApplicationDTO applicationDTO) {
        return new ResponseEntity<>(paymentGatewayService.getStatusOfApplication(applicationDTO), HttpStatus.OK);
    }
}