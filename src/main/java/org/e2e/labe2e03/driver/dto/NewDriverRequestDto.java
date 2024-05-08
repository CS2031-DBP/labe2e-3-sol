package org.e2e.labe2e03.driver.dto;

import lombok.Data;
import org.e2e.labe2e03.driver.domain.Category;
import org.e2e.labe2e03.user.domain.Role;
import org.e2e.labe2e03.vehicle.domain.Vehicle;

import java.time.ZonedDateTime;

@Data
public class NewDriverRequestDto {
    private Category category;
    private Vehicle vehicle;
    private Role role;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private ZonedDateTime createdAt;
}
