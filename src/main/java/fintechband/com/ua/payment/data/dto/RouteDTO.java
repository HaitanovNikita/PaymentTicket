package fintechband.com.ua.payment.data.dto;

import lombok.*;

/**
 * @author nhaitanov
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RouteDTO {
    private long id;
    private String routeNumber;
}
