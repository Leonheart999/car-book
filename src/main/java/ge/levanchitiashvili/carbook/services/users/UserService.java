package ge.levanchitiashvili.carbook.services.users;



import ge.levanchitiashvili.carbook.dtos.security.UserDTO;
import ge.levanchitiashvili.carbook.models.security.User;
import ge.levanchitiashvili.carbook.requests.security.RegisterRequest;
import ge.levanchitiashvili.carbook.requests.users.UserEditRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerUser(RegisterRequest registerRequest);

    User get(long id);

    User getAuthorisedUser();

    User save(User user);

    User edit(long id, UserEditRequest userEditRequest);


    void addNew(User user);

    Optional<User> findByUsername(String usernameOrEmail);

    void changePassword(String oldPassword, String newPassword);



     List<UserDTO> ENTITY_DTO_List(List<User> entities);

     Page<UserDTO> ENTITY_DTO_PAGE(Page<User> entitiesPage) ;

     UserDTO ENTITY_DTO(User entity);

}
