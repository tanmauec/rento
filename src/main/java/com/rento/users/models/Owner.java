package com.rento.users.models;

import lombok.Builder;

import java.util.Date;

public class Owner extends User {

    @Builder
    public Owner(String firstName,
                  String lastName,
                  String countryCode,
                  String primaryPhoneNumber,
                  String userId,
                  State state,
                  Date createdAt,
                  Date updatedAt) {
        super(firstName, lastName, countryCode, primaryPhoneNumber, userId, state, createdAt, updatedAt);
        this.type = UserType.OWNER;
    }

}
