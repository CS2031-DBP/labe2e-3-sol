package org.e2e.labe2e03.ride.application;


import org.e2e.labe2e03.ride.EmailService;
import org.e2e.labe2e03.ride.domain.Ride;
import org.e2e.labe2e03.ride.domain.RideService;
import org.e2e.labe2e03.ride.events.HelloEmailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
public class RideController {
    @Autowired
    private RideService rideService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping()
    public ResponseEntity<Void> passengerBookRide(
            @RequestBody Ride rideRequest
    ) {
        rideService.createRide(rideRequest);
        return ResponseEntity.created(null).build();
    }

    @PatchMapping("/assign/{rideId}")
    public ResponseEntity<Void> driverAssignRide(@PathVariable Long rideId) {
        rideService.assignRide(rideId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{rideId}")
    public ResponseEntity<Void> cancelRide(@PathVariable Long rideId) {
        rideService.cancelRide(rideId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Page<Ride>> getRideByUser(
            @PathVariable Long userId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Page<Ride> response = rideService.getRidesByUser(userId, page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/hola")
    public ResponseEntity<String> sendEmail(@RequestParam String email) {
        applicationEventPublisher.publishEvent(new HelloEmailEvent(email));
        return ResponseEntity.ok("¡Hola mundo!");
    }


}