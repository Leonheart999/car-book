package ge.levanchitiashvili.carbook.services.cars;

import ge.levanchitiashvili.carbook.config.EntityToDtoConverter;
import ge.levanchitiashvili.carbook.dtos.cars.CarProvidedServiceDTO;
import ge.levanchitiashvili.carbook.models.cars.CarProvidedService;
import org.springframework.stereotype.Service;

@Service
public class CarProvidedServiceServiceImpl extends EntityToDtoConverter<CarProvidedService, CarProvidedServiceDTO> implements CarProvidedServiceService {
}
