package fintechband.com.ua.payment.web.controller;

import fintechband.com.ua.payment.data.dto.RouteDTO;
import fintechband.com.ua.payment.service.RouteService;
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
@RequestMapping("/route")
@Api(value = "Endpoint to manage route",
        description = "Operations pertaining to route lifecycle")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of all routes")
    public ResponseEntity<List<RouteDTO>> findAll() {
        log.debug("View a list of all routes");
        return new ResponseEntity<>(routeService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "View a route by id")
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteDTO> findById(@PathVariable Long id) {
        log.debug("View a list of routes by [id:{}]",id);
        return new ResponseEntity<>(routeService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create new route")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created route"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<RouteDTO> create(@RequestBody RouteDTO routeDTO) {
        log.debug("Create new route ");
        return new ResponseEntity<>(routeService.save(routeDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Update route")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated route"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<RouteDTO> update(@RequestBody RouteDTO routeDTO) {
        log.debug("Update route with [id:{}]",routeDTO.getId());
        return new ResponseEntity<>(routeService.save(routeDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete status by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        log.debug("Delete route with [id:{}]",id);
        routeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
