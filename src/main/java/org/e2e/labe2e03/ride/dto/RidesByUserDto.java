package org.e2e.labe2e03.ride.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RidesByUserDto {
    private Long id;
    private String originName;
    private String destinationName;
    private Double price;
    private LocalDateTime departureDate;
}