package ge.levanchitiashvili.carbook.requests.cars;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CarEditRequest {
    @NotBlank(message = "stateNumber can not be blank")
    private String stateNumber;
    @NotBlank(message = "winCode can not be blank")
    private String winCode;
    private String color;
    private String model;
    private String brand;
}
