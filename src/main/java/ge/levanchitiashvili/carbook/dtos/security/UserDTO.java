package ge.levanchitiashvili.carbook.dtos.security;
import lombok.Data;


@Data
public class UserDTO {
    private Long id;
    private String username;
    private String newPassword;
    private Boolean active;

}
