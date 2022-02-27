package com.rento.users.models;

import com.rento.property.models.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class User {
    protected UserType type;
    private String firstName;
    private String lastName;
    private boolean isVerified;
    private String userId;
    private String countryCode;
    private String primaryPhoneNumber;
    private State state;
    private String deviceId;
    private Date createdAt;
    private Date updatedAt;

    public User(String firstName,
                String lastName,
                String countryCode,
                String primaryPhoneNumber,
                String userId,
                State state,
                Date createdAt,
                Date updatedAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.countryCode = countryCode;
        this.primaryPhoneNumber = primaryPhoneNumber;
        this.userId = userId;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
