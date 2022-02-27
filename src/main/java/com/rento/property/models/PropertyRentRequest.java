package com.rento.property.models;

import com.rento.users.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class PropertyRentRequest {
    private Set<User> tenants;
    private double rentAmount;
    private double depositAmount;
    private int rentPayDayOfMonth;
    private int rentDurationInMonths;
    private Date agreementFrom;
    private Date agreementTill;
}
