package ge.levanchitiashvili.carbook.models.cars;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ge.levanchitiashvili.carbook.models.security.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Data
@Entity
@FieldNameConstants
@Table(name = "cars")
@SequenceGenerator(name = "carIdSeq", sequenceName = "cars_id_seq", allocationSize = 1)
public class Car {
    @Id
    @GeneratedValue(generator = "carIdSeq",strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "state_number", nullable = false,unique = true)
    private String stateNumber;
    @Column(name = "win_code",nullable = false,unique = true)
    private String winCode;
    @Column(name = "color")
    private String color;
    @Column(name = "model")
    private String model;
    @Column(name = "brand")
    private String brand;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "user_id",nullable = false)
    private Long userId;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
    @JsonManagedReference("CarProvideService")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH,mappedBy = "car")
    private List<CarProvidedService> carProvidedServices;
}
