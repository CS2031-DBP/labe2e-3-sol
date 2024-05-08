package org.e2e.labe2e03.ride.infrastructure;

import org.e2e.labe2e03.coordinate.domain.Coordinate;
import org.e2e.labe2e03.driver.domain.Category;
import org.e2e.labe2e03.driver.domain.Driver;
import org.e2e.labe2e03.passenger.domain.Passenger;
import org.e2e.labe2e03.ride.domain.Ride;
import org.e2e.labe2e03.ride.domain.Status;
import org.e2e.labe2e03.user.domain.Role;
import org.e2e.labe2e03.vehicle.domain.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RideRepositoryTest {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Passenger passenger;
    private Driver driver;

    @BeforeEach
    public void setUp() {
        createPassengerAndDriver();
        setupAndPersistTestRides();
    }

    private void createPassengerAndDriver() {
        passenger = createPassenger();
        driver = createDriver();
    }

    private Passenger createPassenger() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Jane");
        passenger.setLastName("Doe");
        passenger.setEmail("jane@example.com");
        passenger.setPassword("password");
        passenger.setPhoneNumber("0987654321");
        passenger.setRole(Role.PASSENGER);
        passenger.setCreatedAt(ZonedDateTime.now());
        return entityManager.persist(passenger);
    }

    private Driver createDriver() {
        Vehicle vehicle = createVehicle();
        Coordinate coordinate = new Coordinate(40.730610, -73.935242);

        Driver driver = new Driver();
        driver.setFirstName("John");
        driver.setLastName("Doe");
        driver.setEmail("john@example.com");
        driver.setPassword("password");
        driver.setPhoneNumber("1234567890");
        driver.setCategory(Category.X);
        driver.setVehicle(vehicle);
        driver.setRole(Role.DRIVER);
        driver.setCreatedAt(ZonedDateTime.now());
        driver.setCoordinate(coordinate);
        return entityManager.persist(driver);
    }

    private Vehicle createVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Mercedes-Benz");
        vehicle.setModel("GLB 200");
        vehicle.setLicensePlate("ABC123");
        vehicle.setFabricationYear(2020);
        vehicle.setCapacity(5);
        return entityManager.persist(vehicle);
    }

    private void setupAndPersistTestRides() {
        for (Status status : Status.values()) {
            Ride ride = createTestRide(status);
            entityManager.persist(ride);
        }
    }

    private Ride createTestRide(Status status) {
        Coordinate origin = new Coordinate(10.0 + Math.random() * 20, 20.0 + Math.random() * 20);
        Coordinate destination = new Coordinate(30.0 + Math.random() * 20, 40.0 + Math.random() * 20);

        Ride ride = new Ride();
        ride.setPassenger(passenger);
        ride.setDriver(driver);
        ride.setOriginCoordinates(origin);
        ride.setDestinationCoordinates(destination);
        ride.setOriginName("Origin");
        ride.setDestinationName("Destination");
        ride.setPrice(100.0);
        ride.setStatus(status);
        ride.setDepartureDate(LocalDateTime.now());
        ride.setArrivalDate(LocalDateTime.now().plusHours(2));
        return ride;
    }

    @Test
    public void testCreateRide() {
        Ride ride = createTestRide(Status.REQUESTED);

        Ride savedRide = rideRepository.save(ride);
        Ride retrievedRide = entityManager.find(Ride.class, savedRide.getId());

        assertRidesEqual(ride, retrievedRide);
    }

    @Test
    public void testFindById() {
        Ride ride = createTestRide(Status.REQUESTED);
        Ride savedRide = entityManager.persist(ride);

        Ride retrievedRide = rideRepository.findById(savedRide.getId()).orElse(null);

        assertNotNull(retrievedRide);
        assertEquals(savedRide, retrievedRide);
    }

    @Test
    public void testDeleteById() {
        Ride ride = createTestRide(Status.REQUESTED);
        Ride savedRide = entityManager.persist(ride);

        rideRepository.deleteById(savedRide.getId());

        Ride retrievedRide = entityManager.find(Ride.class, savedRide.getId());

        assertNull(retrievedRide);
    }

    @Test
    public void testFindAllByArrivalDateAndDestinationCoordinates() {
        LocalDateTime arrivalDate = LocalDateTime.now().plusHours(2);
        Coordinate destinationCoordinates = new Coordinate(40.0, -75.0);
        Ride ride = createTestRide(Status.COMPLETED);
        ride.setArrivalDate(arrivalDate);
        ride.setDestinationCoordinates(destinationCoordinates);
        entityManager.persist(ride);

        Optional<Ride> optionalRide = rideRepository.findAllByArrivalDateAndDestinationCoordinates(arrivalDate, destinationCoordinates);

        assertTrue(optionalRide.isPresent());
        assertEquals(ride, optionalRide.get());
    }


    @Test
    public void testFindAllByPassengerIdAndStatus() {
        Page<Ride> ridesPage = rideRepository.findAllByPassengerIdAndStatus(passenger.getId(), Status.COMPLETED, PageRequest.of(0, 10));

        assertEquals(1, ridesPage.getTotalElements());
        Ride retrievedRide = ridesPage.getContent().get(0);
        assertEquals(Status.COMPLETED, retrievedRide.getStatus());
    }

    private void assertRidesEqual(Ride expected, Ride actual) {
        assertEquals(expected.getPassenger(), actual.getPassenger());
        assertEquals(expected.getDriver(), actual.getDriver());
        assertEquals(expected.getOriginCoordinates(), actual.getOriginCoordinates());
        assertEquals(expected.getDestinationCoordinates(), actual.getDestinationCoordinates());
        assertEquals(expected.getOriginName(), actual.getOriginName());
        assertEquals(expected.getDestinationName(), actual.getDestinationName());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getDepartureDate(), actual.getDepartureDate());
        assertEquals(expected.getArrivalDate(), actual.getArrivalDate());
    }
}
