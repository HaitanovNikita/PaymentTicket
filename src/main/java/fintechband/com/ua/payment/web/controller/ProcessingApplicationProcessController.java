package fintechband.com.ua.payment.web.controller;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.service.impl.ProcessingApplicationProcessServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nhaitanov
 */
@Slf4j
@RestController
@RequestMapping("/processing-application")
@Api(value = "Endpoint to manage application",
        description = "Operations pertaining to application lifecycle")
public class ProcessingApplicationProcessController {
    @Autowired
    private ProcessingApplicationProcessServiceImpl processingApplicationProcessService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Processing all application")
    public List<ApplicationDTO> allApplicationsProcessing() {
        log.debug("Processing all application");
        return processingApplicationProcessService.allApplicationsProcessing();
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Processing application")
    public ApplicationDTO applicationProcessing(@PathVariable Long id) {
        log.debug("Processing application by [id:{}]",id);
        return processingApplicationProcessService.applicationProcessing(id);
    }
}
