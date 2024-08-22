package ge.levanchitiashvili.carbook.repositories.jpa.users;



import ge.levanchitiashvili.carbook.config.BaseRepository;
import ge.levanchitiashvili.carbook.models.security.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPARepository extends BaseRepository<User, Long> {
    Optional<User> findByUsernameAndActiveTrue(String username);

    boolean existsByUsername(String username);



}
