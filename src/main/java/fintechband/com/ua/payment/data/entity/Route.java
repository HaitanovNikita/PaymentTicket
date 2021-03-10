package fintechband.com.ua.payment.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nhaitanov
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "route_number")
    private String route_number;

    @OneToMany(mappedBy = "route",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Application> application = new ArrayList<>();
}
