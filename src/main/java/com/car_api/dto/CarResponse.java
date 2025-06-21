package com.car_api.dto;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CarResponse {
    private String model;
    private String plateNumber;
    private String color;
}
