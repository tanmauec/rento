package com.rento.property.models;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Address {
    private long id;

    @Pattern(regexp = "[0-9]{6}|[0-9]{3}\\s[0-9]{3}", message = "Please provide a valid PIN Code")
    private String pin;

    private String houseNo;

    private String street;

    private String locality;

    private String landmark;

    private String city;

    private String state;


    @NotEmpty
    @NotNull
    private String tag;

    private String name;

    private String phoneNumber;

    @NotEmpty
    @NotNull
    private String address;

    private boolean primary;

    private boolean active=true;

    private String scope;

    private String uniqueId;

}