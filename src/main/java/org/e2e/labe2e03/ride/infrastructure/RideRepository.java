package org.e2e.labe2e03.ride.infrastructure;

import org.e2e.labe2e03.coordinate.domain.Coordinate;
import org.e2e.labe2e03.ride.domain.Status;
import org.e2e.labe2e03.ride.domain.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride, Long> {
    Page<Ride> findAllByPassengerIdAndStatus(Long passenger_id, Status status, Pageable pageable);
    Optional<Ride> findAllByArrivalDateAndDestinationCoordinates(LocalDateTime arrivalDate, Coordinate destinationCoordinates);
}