package com.rento.users.services;

import com.rento.users.models.CreateTenantRequest;
import com.rento.users.models.CreateUserRequest;
import com.rento.users.models.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(String userId);

    Optional<User> getUserByPhoneNumber(String countryCode, String phoneNumber);

    User createUser(CreateUserRequest request);

}
