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
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "status_name")
    private String status_name;

    @OneToMany(mappedBy = "status",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Application> application = new ArrayList<>();
}
