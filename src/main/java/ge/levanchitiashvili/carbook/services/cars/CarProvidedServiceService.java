package ge.levanchitiashvili.carbook.services.cars;

import ge.levanchitiashvili.carbook.dtos.cars.CarDTO;
import ge.levanchitiashvili.carbook.dtos.cars.CarProvidedServiceDTO;
import ge.levanchitiashvili.carbook.models.cars.CarProvidedService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarProvidedServiceService   {

    List<CarProvidedServiceDTO> ENTITY_DTO_List(List<CarProvidedService> entities);

    Page<CarProvidedServiceDTO> ENTITY_DTO_PAGE(Page<CarProvidedService> entitiesPage);

    CarProvidedServiceDTO ENTITY_DTO(CarProvidedService entity);
}
