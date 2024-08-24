package ge.levanchitiashvili.carbook.services.users;

import ge.levanchitiashvili.carbook.config.EntityToDtoConverter;
import ge.levanchitiashvili.carbook.dtos.security.UserDTO;
import ge.levanchitiashvili.carbook.models.security.User;
import ge.levanchitiashvili.carbook.repositories.jpa.users.UserJPARepository;
import ge.levanchitiashvili.carbook.requests.security.RegisterRequest;
import ge.levanchitiashvili.carbook.requests.users.UserEditRequest;
import ge.levanchitiashvili.carbook.services.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends EntityToDtoConverter<User, UserDTO> implements UserService {
    private final UserJPARepository repository;
    @Lazy
    private final SecurityService securityService;

    @Override
    public User get(long id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException(String.format("User with with id %s", id));
        }
        return user.get();
    }

    @Override
    public User getAuthorisedUser() {
        long userId= SecurityService.getCurrentUserId();
        return get(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNew(User user) {
        if (repository.existsByUsername(user.getUsername())){
            throw new RuntimeException(String.format("User with username %s already exists", user.getUsername()));
        }
        user.setActive(true);
        user.setPassword(securityService.getEncodedPassword(user.getPassword()));
        save(user);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User edit(long id, UserEditRequest userEditRequest) {
        User oldUser = get(id);
        oldUser.setUsername(userEditRequest.getUsername());
        return save(oldUser);
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsernameAndActiveTrue(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String oldPassword, String newPassword) {
        securityService.changePassword(oldPassword, newPassword);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(RegisterRequest registerRequest) {
        if (!registerRequest.getPassword().equals(registerRequest.getRepeatPassword()))
            throw new RuntimeException("repeat password doesn't match");
        User user = new User();
        user.setPassword(registerRequest.getPassword());
        user.setUsername(registerRequest.getUserName());
        validateUser(user);
        addNew(user);
    }

    private void validateUser(User user){
        Optional<User> checker= repository.findByUsernameAndActiveTrue(user.getUsername());
        if(checker.isPresent() && (user.getId()==null || !checker.get().getId().equals(user.getId()))){
            throw new RuntimeException("User with username " + user.getUsername() + " already exists");
        }
    }

}
