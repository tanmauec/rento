package com.rento.users.models;

import com.rento.property.models.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class Tenant extends User {

    @Builder
    public Tenant(String firstName,
                  String lastName,
                  String countryCode,
                  String primaryPhoneNumber,
                  String userId,
                  State state,
                  Date createdAt,
                  Date updatedAt) {
        super(firstName, lastName, countryCode, primaryPhoneNumber, userId, state, createdAt, updatedAt);
        this.type = UserType.TENANT;
    }

}
