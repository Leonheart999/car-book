package ge.levanchitiashvili.carbook.controllers.cars;

import ge.levanchitiashvili.carbook.dtos.cars.CarDTO;

import ge.levanchitiashvili.carbook.dtos.cars.CarProvidedServiceDTO;
import ge.levanchitiashvili.carbook.requests.cars.CarEditRequest;
import ge.levanchitiashvili.carbook.requests.cars.CarSearchRequest;
import ge.levanchitiashvili.carbook.requests.cars.NewCarProvidedServiceRequest;
import ge.levanchitiashvili.carbook.requests.cars.NewCarRequest;
import ge.levanchitiashvili.carbook.services.cars.CarProvidedServiceService;
import ge.levanchitiashvili.carbook.services.cars.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CarProvidedServiceService carProvidedServiceService;

    @GetMapping()
    public Page<CarDTO> search(CarSearchRequest carSearchRequest, Pageable pageable) {
        return carService.ENTITY_DTO_PAGE(carService.search(carSearchRequest, pageable));
    }

    @PostMapping()
    public CarDTO save(@Valid @RequestBody NewCarRequest newCarRequest) {
        return carService.ENTITY_DTO(carService.addNew(newCarRequest));
    }

    @PutMapping("{id}")
    public CarDTO edit(@PathVariable long id, @Valid @RequestBody CarEditRequest carEditRequest) {
        return carService.ENTITY_DTO(carService.edit(id, carEditRequest));
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        carService.delete(id);
    }

    @GetMapping("{id}/provided_services")
    public Page<CarProvidedServiceDTO> providedServices(@PathVariable long id, Pageable pageable) {
        return carProvidedServiceService.ENTITY_DTO_PAGE(carService.getCarProvidedServices(id, pageable));
    }

    @PostMapping("{id}/provided_services")
    public CarProvidedServiceDTO saveCarProvidedService(@PathVariable long id, @Valid @RequestBody NewCarProvidedServiceRequest newCarProvidedServiceRequest) {
        return carProvidedServiceService.ENTITY_DTO(carService.addCarProvidedService(id, newCarProvidedServiceRequest));
    }
}
