package fintechband.com.ua.payment.data.converter;

import fintechband.com.ua.payment.data.dto.ApplicationDTO;
import fintechband.com.ua.payment.data.dto.RouteDTO;
import fintechband.com.ua.payment.data.dto.StatusDTO;
import fintechband.com.ua.payment.data.entity.Application;
import fintechband.com.ua.payment.data.entity.Route;
import fintechband.com.ua.payment.data.entity.Status;

/**
 * @author nhaitanov
 */
public class Converter {
    public static Application convertDtoToEntity(ApplicationDTO applicationDTO) {
        return Application
                .builder()
                .id(applicationDTO.getId())
                .dateTimeDispatch(applicationDTO.getLocalDateTimeDispatch())
                .build();
    }

    public static ApplicationDTO convertEntityToDto(Application application) {
        return ApplicationDTO
                .builder()
                .id(application.getId())
                .localDateTimeDispatch(application.getDateTimeDispatch())
                .routeNumber(application.getRoute().getRoute_number())
                .status(application.getStatus().getStatus_name())
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