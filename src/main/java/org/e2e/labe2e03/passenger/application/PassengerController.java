package org.e2e.labe2e03.passenger.application;

import org.e2e.labe2e03.coordinate.domain.Coordinate;
import org.e2e.labe2e03.passenger.domain.Passenger;
import org.e2e.labe2e03.passenger.domain.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;


    /*
        POST /passenger
        Request Body: PassengerRequestDto
        Response Body: Void
        Response Status: 201 Created
     */


    @GetMapping("/{id}")
    ResponseEntity<Passenger> getPassenger(@PathVariable Long id) {
        return ResponseEntity.ok(passengerService.getPassenger(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/places")
    ResponseEntity<Void> addPlace(
            @PathVariable Long id,
            @RequestBody Coordinate coordinate,
            @RequestParam String description) {
        passengerService.addPassengerPlace(id, coordinate, description);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/places/{coordinateId}")
    ResponseEntity<Void> deletePlace(
            @PathVariable Long id,
            @PathVariable Long coordinateId) {
        passengerService.deletePassengerPlace(id, coordinateId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/places")
    ResponseEntity<List<Coordinate>> getPlaces(@PathVariable Long id) {
        return ResponseEntity.ok(passengerService.getPassengerPlaces(id));
    }

}