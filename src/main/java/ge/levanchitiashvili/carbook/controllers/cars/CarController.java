package ge.levanchitiashvili.carbook.controllers.cars;

import ge.levanchitiashvili.carbook.dtos.cars.CarDTO;

import ge.levanchitiashvili.carbook.requests.cars.CarEditRequest;
import ge.levanchitiashvili.carbook.requests.cars.CarSearchRequest;
import ge.levanchitiashvili.carbook.requests.cars.NewCarRequest;
import ge.levanchitiashvili.carbook.services.cars.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    @GetMapping()
    public Page<CarDTO> search(CarSearchRequest carSearchRequest, Pageable pageable) {
        return carService.ENTITY_DTO_PAGE(carService.search(carSearchRequest, pageable));
    }
    @PostMapping()
    public CarDTO save(@RequestBody NewCarRequest newCarRequest) {
        return carService.ENTITY_DTO(carService.addNew(newCarRequest));
    }
    @PutMapping("{id}")
    public CarDTO edit(@PathVariable long id, @RequestBody CarEditRequest carEditRequest) {
        return carService.ENTITY_DTO(carService.edit(id, carEditRequest));
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        carService.delete(id);
    }
}
