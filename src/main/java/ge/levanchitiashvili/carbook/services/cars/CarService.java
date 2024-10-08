package ge.levanchitiashvili.carbook.services.cars;

import ge.levanchitiashvili.carbook.dtos.cars.CarDTO;
import ge.levanchitiashvili.carbook.models.cars.Car;
import ge.levanchitiashvili.carbook.models.cars.CarProvidedService;
import ge.levanchitiashvili.carbook.requests.cars.CarEditRequest;
import ge.levanchitiashvili.carbook.requests.cars.CarSearchRequest;
import ge.levanchitiashvili.carbook.requests.cars.NewCarProvidedServiceRequest;
import ge.levanchitiashvili.carbook.requests.cars.NewCarRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {

    Page<Car> search(CarSearchRequest carSearchRequest, Pageable pageable);

    Car get(long id);

    Car addNew(NewCarRequest newCarRequest);

    Car save(Car car);

    Car edit(long id, CarEditRequest carEditRequest);

    void delete(long id);

    Page<CarProvidedService> getCarProvidedServices(long carId, Pageable pageable);

    CarProvidedService getCarProvidedService(long carProvidedServiceId);

    CarProvidedService addCarProvidedService(long carId, NewCarProvidedServiceRequest newCarProvidedServiceRequest);

    CarProvidedService saveCarProvidedService(CarProvidedService carProvidedService);

     void deleteCarProvidedService(long carProvidedServiceId);

    List<CarDTO> ENTITY_DTO_List(List<Car> entities);

    Page<CarDTO> ENTITY_DTO_PAGE(Page<Car> entitiesPage);

    CarDTO ENTITY_DTO(Car entity);

}
