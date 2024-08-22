package ge.levanchitiashvili.carbook.models.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "shop",name = "users")
@SequenceGenerator(name = "userIdSeq", sequenceName = "shop.users_id_seq", allocationSize = 1)
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
}
