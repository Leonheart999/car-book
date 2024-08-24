package ge.levanchitiashvili.carbook.requests.cars;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class NewCarProvidedServiceRequest {
    @NotBlank(message = "provided service can not be blank")
    private String providedService;
    @DateTimeFormat(pattern = "yyyy-MM.dd")
    private LocalDate date;
    @NotNull(message = "price can not be null")
    private BigDecimal price;
}
