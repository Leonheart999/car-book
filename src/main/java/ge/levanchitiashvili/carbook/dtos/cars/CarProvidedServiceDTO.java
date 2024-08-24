package ge.levanchitiashvili.carbook.dtos.cars;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CarProvidedServiceDTO {
    private Long id;
    private String providedService;
    private LocalDate date;
    private BigDecimal price;
}
