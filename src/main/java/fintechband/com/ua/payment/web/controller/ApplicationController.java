package fintechband.com.ua.payment.web.controller;

import fintechband.com.ua.payment.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nhaitanov
 */
@Slf4j
@RestController
@RequestMapping("/application")
@Api(value = "Endpoint to manage application",
        description = "Operations pertaining to application lifecycle")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of all applications")
    public ResponseEntity<List<String>> findAll() {
        log.debug("Find all application for payment ticket");
        return new ResponseEntity<>(applicationService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "View a application by id")
    @GetMapping(value = "/find/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findById(@PathVariable Long id) {
        log.debug("Find application by id for payment ticket");
        return new ResponseEntity<>(applicationService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "View all valid application by status")
    @GetMapping(value = "/findAll/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> findAllByStatus(@PathVariable Long status) {
        log.debug("Find all application for payment ticket with [status:{}]", status);
        return new ResponseEntity<>(applicationService.findAllByStatus(status), HttpStatus.OK);
    }

    @ApiOperation(value = "View one valid application by status")
    @GetMapping(value = "/find/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findByStatus(@PathVariable Long status) {
        log.debug("Find application for payment ticket with [status:{}]", status);
        return new ResponseEntity<>(applicationService.findByStatus(status), HttpStatus.OK);
    }

    @ApiOperation(value = "Create new application")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created application"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Long> create(@RequestBody String xmlApplicationRequest) {
        log.debug("Create application for payment ticket");
        return new ResponseEntity<>(applicationService.save(xmlApplicationRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Update application")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated route"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Long> update(@RequestBody String xmlApplicationRequest) {
        log.debug("Update application for payment ticket");
        return new ResponseEntity<>(applicationService.update(xmlApplicationRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete application by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        log.debug("Delete application for payment ticket with  [id:{}]", id);
        applicationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
