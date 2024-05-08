package org.e2e.labe2e03.coordinate.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CoordinateDto {
    private Long id;

    @DecimalMin(value = "-90")
    @DecimalMax(value = "90")
    @Digits(integer = 3, fraction = 6)
    @NotNull
    private Double latitude;

    @DecimalMin(value = "-180")
    @DecimalMax(value = "180")
    @Digits(integer = 3, fraction = 6)
    @NotNull
    private Double longitude;
}