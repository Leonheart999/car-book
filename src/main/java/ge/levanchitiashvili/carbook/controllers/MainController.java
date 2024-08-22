package ge.levanchitiashvili.carbook.controllers;

import ge.levanchitiashvili.carbook.requests.security.RegisterRequest;
import ge.levanchitiashvili.carbook.services.users.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {
    private final UserService userService;

    @PostMapping("/register")
    public void welcome(@Valid @RequestBody RegisterRequest registerRequest){
        userService.registerUser(registerRequest);
    }


}
