package fintechband.com.ua.payment.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author nhaitanov
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "route_id")
    private Long route_id;
    @Column(name="date_time_dispatch")
    private LocalDateTime dateTimeDispatch;
    @Column(name = "status_id")
    private Long route_id;

}
