package com.rento.users.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOwnerRequest {
    @NotBlank
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date dateOfBirth;
    private String password;
    @NotBlank
    private String countryCode;
    @NotBlank
    private String phoneNumber;
    private String emailId;
    private State state;
}
