package fintechband.com.ua.payment.data.controller;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.data.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nhaitanov
 */
@RestController
@RequestMapping("/application")
@Api(value = "Endpoint to manage application",
        description = "Operations pertaining to application lifecycle")
public class AutomobileController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of all applications")
    public ResponseEntity<List<ApplicationDTO>> findAll() {
        return new ResponseEntity<>(applicationService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "View a application by id")
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(applicationService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "View all valid application by status")
    @GetMapping(value = "/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ApplicationDTO>> findByStatus(@PathVariable Long status) {
        return new ResponseEntity<>(applicationService.findByStatus(status), HttpStatus.OK);
    }

    @ApiOperation(value = "Create new application")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created application"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<ApplicationDTO> create(@RequestBody ApplicationDTO applicationDTO) {
        return new ResponseEntity<>(applicationService.save(applicationDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Update application")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated route"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<ApplicationDTO> update(@RequestBody ApplicationDTO applicationDTO) {
        return new ResponseEntity<>(applicationService.save(applicationDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete application by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        applicationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}