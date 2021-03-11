package fintechband.com.ua.payment.data.converter;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.data.dto.RouteDTO;
import fintechband.com.ua.payment.data.dto.StatusDTO;
import fintechband.com.ua.payment.data.entity.Application;
import fintechband.com.ua.payment.data.entity.Route;
import fintechband.com.ua.payment.data.entity.Status;
import fintechband.com.ua.payment.validation.model.application.ApplicationRequest;
import fintechband.com.ua.payment.validation.model.application.ApplicationResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author nhaitanov
 */
public class Converter {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ApplicationResponse convertDtoToModel(ApplicationDTO applicationDTO) {
        return ApplicationResponse
                .builder()
                .id(applicationDTO.getId())
                .route(applicationDTO.getId())
                .localDateTimeDispatch(applicationDTO.getLocalDateTimeDispatch().format(formatter))
                .status(applicationDTO.getId())
                .build();
    }

    public static ApplicationDTO convertModelToDto(ApplicationRequest application) {
        return ApplicationDTO
                .builder()
                .id(application.getId())
                .localDateTimeDispatch(LocalDateTime.parse(application.getLocalDateTimeDispatch(), formatter))
                .route(application.getRoute()==0?1L:application.getRoute())
                .status(application.getStatus()==0?1L:application.getStatus())
                .build();
    }

    public static Application convertDtoToEntity(ApplicationDTO applicationDTO) {
        return Application
                .builder()
                .id(applicationDTO.getId())
                .route(Route.builder().id(applicationDTO.getRoute()).build())
                .dateTimeDispatch(applicationDTO.getLocalDateTimeDispatch())
                .status(Status.builder().id(applicationDTO.getStatus()).build())
                .build();
    }

    public static ApplicationDTO convertEntityToDto(Application application) {
        return ApplicationDTO
                .builder()
                .id(application.getId())
                .localDateTimeDispatch(application.getDateTimeDispatch())
                .route(application.getRoute().getId()!=null?application.getRoute().getId():null)
                .status(application.getStatus().getId()!=null?application.getStatus().getId():null)
                .build();
    }

    public static Route convertDtoToEntity(RouteDTO routeDTO) {
        return Route
                .builder()
                .id(routeDTO.getId())
                .route_number(routeDTO.getRouteNumber())
                .build();
    }

    public static RouteDTO convertEntityToDto(Route route) {
        return RouteDTO
                .builder()
                .id(route.getId())
                .routeNumber(route.getRoute_number())
                .build();
    }

    public static Status convertDtoToEntity(StatusDTO statusDTO) {
        return Status
                .builder()
                .id(statusDTO.getId())
                .status_name(statusDTO.getStatusName())
                .build();
    }

    public static StatusDTO convertEntityToDto(Status status) {
        return StatusDTO
                .builder()
                .id(status.getId())
                .statusName(status.getStatus_name())
                .build();
    }
}
