package ge.levanchitiashvili.carbook.controllers.users;



import ge.levanchitiashvili.carbook.dtos.security.UserDTO;
import ge.levanchitiashvili.carbook.requests.users.UserEditRequest;
import ge.levanchitiashvili.carbook.services.users.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class UserController {
    private final UserService userService;

    @GetMapping("current")
    public UserDTO getCurrentUser() {
        return userService.ENTITY_DTO(userService.getAuthorisedUser());
    }

    @GetMapping("{id}")
    public UserDTO get(@PathVariable long id) {
         return userService.ENTITY_DTO(userService.get(id));
    }


    @PutMapping("{id}")
    public UserDTO edit(@PathVariable long id, @Valid @RequestBody UserEditRequest userEditRequest) {
        return userService.ENTITY_DTO(userService.edit(id, userEditRequest));
    }


    @PostMapping("/change-password")
    public void changePassword(@RequestParam String oldPassword,@RequestParam String newPassword){
        userService.changePassword(oldPassword,newPassword);
    }


}
