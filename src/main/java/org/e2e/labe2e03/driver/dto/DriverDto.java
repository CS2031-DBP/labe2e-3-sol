package org.e2e.labe2e03.driver.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.e2e.labe2e03.driver.domain.Category;
import org.e2e.labe2e03.vehicle.dto.VehicleBasicDto;

@Data
public class DriverDto {
    private Long id;
    private Category category;
    private String firstName;
    private String lastName;
    private Integer trips;
    private Float avgRating;

    @NotNull
    @Valid
    private VehicleBasicDto vehicle;
}