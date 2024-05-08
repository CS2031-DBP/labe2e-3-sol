package org.e2e.labe2e03.passenger.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.e2e.labe2e03.coordinate.dto.CoordinateDto;

@Data
public class PassengerLocationResponseDto {

    @NotNull
    @Valid
    private CoordinateDto coordinate;

    private String description;
}
