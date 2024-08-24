package ge.levanchitiashvili.carbook.requests.cars;

import lombok.Getter;

@Getter
public class CarSearchRequest {
    private String stateNumber;
    private String winCode;
    private String color;
    private String model;
    private String brand;
    private Boolean onlyActive;
}
