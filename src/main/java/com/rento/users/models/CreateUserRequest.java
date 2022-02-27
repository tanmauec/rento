package com.rento.users.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    private UserType type;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String password;
    private String countryCode;
    private String phoneNumber;
    private String emailId;
    private State state;
}
