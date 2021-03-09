package fintechband.com.ua.payment.data.dto;

import lombok.*;

/**
 * @author nhaitanov
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class StatusDTO {
    private long id;
    private String statusName;
}
