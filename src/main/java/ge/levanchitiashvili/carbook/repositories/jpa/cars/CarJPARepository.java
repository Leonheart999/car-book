package ge.levanchitiashvili.carbook.repositories.jpa.cars;



import ge.levanchitiashvili.carbook.config.BaseRepository;
import ge.levanchitiashvili.carbook.models.cars.Car;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarJPARepository extends BaseRepository<Car, Long> {

    Optional<Car> findByIdAndActiveTrue(long id);

    Optional<Car> findByStateNumberAndActiveTrue(String stateNumber);

    Optional<Car> findByWinCodeAndActiveTrue(String stateNumber);
}
