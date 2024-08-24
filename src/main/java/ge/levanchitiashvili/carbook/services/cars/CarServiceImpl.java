package ge.levanchitiashvili.carbook.services.cars;

import ge.levanchitiashvili.carbook.config.EntityToDtoConverter;

import ge.levanchitiashvili.carbook.dtos.cars.CarDTO;
import ge.levanchitiashvili.carbook.models.cars.Car;
import ge.levanchitiashvili.carbook.repositories.jpa.cars.CarJPARepository;
import ge.levanchitiashvili.carbook.repositories.jpa.cars.CarProvidedServiceJPARepository;
import ge.levanchitiashvili.carbook.requests.cars.CarEditRequest;

import ge.levanchitiashvili.carbook.requests.cars.CarSearchRequest;
import ge.levanchitiashvili.carbook.requests.cars.NewCarRequest;
import ge.levanchitiashvili.carbook.services.security.SecurityService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl extends EntityToDtoConverter<Car, CarDTO> implements CarService {
    private final CarJPARepository repository;
    private final CarProvidedServiceJPARepository carProvidedServiceJPARepository;


    @Override
    public Page<Car> search(CarSearchRequest carSearchRequest, Pageable pageable) {
        long userId = SecurityService.getCurrentUserId();
        return repository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            predicate = cb.and(predicate, cb.equal(root.get(Car.Fields.userId), userId));
            if (BooleanUtils.isTrue(carSearchRequest.getOnlyActive())) {
                predicate = cb.and(predicate, cb.equal(root.get(Car.Fields.active), true));
            }
            if (carSearchRequest.getBrand() != null && !carSearchRequest.getBrand().isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get(Car.Fields.brand), carSearchRequest.getBrand()));
            }
            if (carSearchRequest.getModel() != null && !carSearchRequest.getModel().isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get(Car.Fields.model), carSearchRequest.getModel()));
            }
            if (carSearchRequest.getColor() != null && !carSearchRequest.getColor().isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get(Car.Fields.color), carSearchRequest.getColor()));
            }
            if (carSearchRequest.getStateNumber() != null && !carSearchRequest.getStateNumber().isEmpty()) {
                predicate = cb.and(predicate, cb.like(root.get(Car.Fields.stateNumber), "%" + carSearchRequest.getStateNumber() + "%"));
            }
            if (carSearchRequest.getWinCode() != null && !carSearchRequest.getWinCode().isEmpty()) {
                predicate = cb.and(predicate, cb.like(root.get(Car.Fields.winCode), "%" + carSearchRequest.getWinCode() + "%"));
            }
            return predicate;
        }, pageable);
    }

    @Override
    public Car get(long id) {
        Optional<Car> Car = repository.findByIdAndActiveTrue(id);
        if (Car.isEmpty()) {
            throw new RuntimeException(String.format("Car with with id %s", id));
        }
        return Car.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Car addNew(NewCarRequest newCarRequest) {
        Car car = new Car();
        car.setStateNumber(newCarRequest.getStateNumber());
        car.setWinCode(newCarRequest.getWinCode());
        car.setBrand(newCarRequest.getBrand());
        car.setModel(newCarRequest.getModel());
        car.setColor(newCarRequest.getColor());
        car.setUserId(SecurityService.getCurrentUserId());
        car.setActive(true);
        validateCar(car);
        return save(car);
    }

    @Override
    public Car save(Car Car) {
        return repository.save(Car);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Car edit(long id, CarEditRequest carEditRequest) {
        Car car = get(id);
        car.setStateNumber(carEditRequest.getStateNumber());
        car.setWinCode(carEditRequest.getWinCode());
        car.setBrand(carEditRequest.getBrand());
        car.setModel(carEditRequest.getModel());
        car.setColor(carEditRequest.getColor());
        validateCar(car);
        return save(car);
    }

    @Override
    public void delete(long id) {
        Car car = get(id);
        car.setActive(false);
        save(car);
    }

    private void validateCar(Car car) {
        StringBuilder errors = new StringBuilder();
        Optional<Car> exists = repository.findByStateNumberAndActiveTrue(car.getStateNumber());
        if (exists.isPresent() && !exists.get().getId().equals(car.getId())) {
            errors.append(String.format("Car with state number %s already exists", car.getStateNumber()));
        }
        exists = repository.findByWinCodeAndActiveTrue(car.getStateNumber());
        if (exists.isPresent() && !exists.get().getId().equals(car.getId())) {
            if (!errors.isEmpty()) {
                errors.append("\n");
            }
            errors.append(String.format("Car with win code %s already exists", car.getWinCode()));
        }
        if (!errors.isEmpty()) {
            throw new RuntimeException(errors.toString());
        }
    }

}
