package org.e2e.labe2e03.vehicle.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VehicleBasicDto {
    @NotNull
    @Size(min = 1, max = 50)
    private String brand;

    @NotNull
    @Size(min = 1, max = 50)
    private String model;

    @NotNull
    @Size(min = 1, max = 20)
    private String licensePlate;

    @NotNull
    @Min(value = 1900)
    private Integer fabricationYear;

    @NotNull
    @Min(value = 1)
    private Integer capacity;
}