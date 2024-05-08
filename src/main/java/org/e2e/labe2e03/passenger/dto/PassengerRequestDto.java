package org.e2e.labe2e03.passenger.dto;

import lombok.Data;

@Data
public class PassengerRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
