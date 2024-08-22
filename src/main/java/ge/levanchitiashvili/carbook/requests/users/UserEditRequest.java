package ge.levanchitiashvili.carbook.requests.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserEditRequest {
    @NotBlank(message = "username can not be blank")
    private  String username;
}
