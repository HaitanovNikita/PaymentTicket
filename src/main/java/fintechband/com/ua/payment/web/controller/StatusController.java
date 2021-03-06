package fintechband.com.ua.payment.web.controller;

import fintechband.com.ua.payment.data.dto.StatusDTO;
import fintechband.com.ua.payment.service.StatusService;
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
@RequestMapping("/status")
@Api(value = "Endpoint to manage status",
        description = "Operations pertaining to status lifecycle")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of all status")
    public ResponseEntity<List<StatusDTO>> findAll() {
        log.debug("Find all statuses");
        return new ResponseEntity<>(statusService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "View a status by id")
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatusDTO> findById(@PathVariable Long id) {
        log.debug("Find status by [id:{}]", id);
        return new ResponseEntity<>(statusService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create new status")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created status"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<StatusDTO> create(@RequestBody StatusDTO statusDTO) {
        log.debug("Create new status");
        return new ResponseEntity<>(statusService.save(statusDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Update status")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated status"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<StatusDTO> update(@RequestBody StatusDTO statusDTO) {
        log.debug("Update status with [id:{}]", statusDTO.getId());
        return new ResponseEntity<>(statusService.save(statusDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete status by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        log.debug("Delete status with [id:{}]", id);
        statusService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
