package ge.levanchitiashvili.carbook.models.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ge.levanchitiashvili.carbook.models.cars.Car;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
@SequenceGenerator(name = "userIdSeq", sequenceName = "users_id_seq", allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(generator = "userIdSeq",strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_name", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    @JsonBackReference
    private String password;
    @Column(name = "active")
    private Boolean active;
    @JsonManagedReference("Car")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH,mappedBy = "user")
    private List<Car> cars;
}
