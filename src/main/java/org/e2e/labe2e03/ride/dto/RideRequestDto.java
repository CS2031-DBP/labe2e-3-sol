package org.e2e.labe2e03.ride.dto;

import jakarta.validation.Valid;
import lombok.Data;
import org.e2e.labe2e03.coordinate.dto.CoordinateDto;
import org.e2e.labe2e03.ride.domain.Status;

@Data
public class RideRequestDto {
    private String originName;
    private String destinationName;
    private Status status;
    private Double price;
    @Valid
    private CoordinateDto originCoordinates;
    @Valid
    private CoordinateDto destinationCoordinates;
    private Long passengerId;
    private Long driverId;
}