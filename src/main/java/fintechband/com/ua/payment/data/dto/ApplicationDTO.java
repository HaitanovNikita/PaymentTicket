package fintechband.com.ua.payment.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author nhaitanov
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ApplicationDTO {
    private long id;
    private String routeNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTimeDispatch;
    private String status;
}
