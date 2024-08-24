package ge.levanchitiashvili.carbook.repositories.jpa.cars;



import ge.levanchitiashvili.carbook.config.BaseRepository;
import ge.levanchitiashvili.carbook.models.cars.CarProvidedService;
import org.springframework.stereotype.Repository;

@Repository
public interface CarProvidedServiceJPARepository extends BaseRepository<CarProvidedService, Long> {

}
