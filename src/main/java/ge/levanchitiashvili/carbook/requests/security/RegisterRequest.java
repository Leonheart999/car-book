package ge.levanchitiashvili.carbook.requests.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterRequest {
    @NotBlank(message = "username can not be blank")
    private String userName;
    @NotBlank(message = "password can not be blank")
    private String password;
    @NotBlank(message = "repeat password can not be blank")
    private String repeatPassword;
}
