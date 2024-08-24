package ge.levanchitiashvili.carbook.models.cars;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ge.levanchitiashvili.carbook.models.security.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "car_provided_services")
@SequenceGenerator(name = "cardProvidedServiceIdSeq", sequenceName = "car_provided_services_id_seq", allocationSize = 1)
public class CarProvidedService {
    @Id
    @GeneratedValue(generator = "cardProvidedServiceIdSeq",strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "provided_service")
    private String providedService;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "car_id")
    private Long carId;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Car car;

}
