package com.rento.users.utils;

import com.google.common.collect.ImmutableMap;
import com.rento.core.exceptions.ErrorCode;
import com.rento.core.exceptions.RentoServiceException;
import com.rento.users.models.*;
import com.rento.users.storage.StoredUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConverterUtil {

    public static User toDto(StoredUser storedUser) {
        if (storedUser == null)
            return null;

        if (storedUser.getType().equals(UserType.TENANT)) {
            return Tenant.builder()
                    .firstName(storedUser.getFirstName())
                    .lastName(storedUser.getLastName())
                    .userId(storedUser.getUserId())
                    .createdAt(storedUser.getCreatedAt())
                    .updatedAt(storedUser.getUpdatedAt())
                    .countryCode(storedUser.getCountryCode())
                    .primaryPhoneNumber(storedUser.getPrimaryPhoneNumber())
                    .state(storedUser.getState())
                    .build();
        }

        if (storedUser.getType().equals(UserType.OWNER)) {
            return Owner.builder()
                    .firstName(storedUser.getFirstName())
                    .lastName(storedUser.getLastName())
                    .userId(storedUser.getUserId())
                    .createdAt(storedUser.getCreatedAt())
                    .updatedAt(storedUser.getUpdatedAt())
                    .countryCode(storedUser.getCountryCode())
                    .primaryPhoneNumber(storedUser.getPrimaryPhoneNumber())
                    .state(storedUser.getState())
                    .build();
        }
        throw RentoServiceException.error(ErrorCode.INTERNAL_ERROR,
                ImmutableMap.of("Cant deserialize type", storedUser.getType()));
    }

    public static StoredUser update(StoredUser existingTenant, CreateTenantRequest request) {
        return StoredUser.builder()
                .id(existingTenant.getId())
                .userId(existingTenant.getUserId()) // not updatable.
                .password(request.getPassword() != null ? request.getPassword() : existingTenant.getPassword())
                .firstName(request.getFirstName() != null ? request.getFirstName() : existingTenant.getFirstName())
                .lastName(request.getLastName() != null ?  request.getLastName(): existingTenant.getLastName())
                .state(request.getState() != null ? request.getState() : existingTenant.getState())
                .countryCode(request.getCountryCode() != null ? request.getCountryCode() : existingTenant.getCountryCode())
                .primaryPhoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : existingTenant.getPrimaryPhoneNumber())
                .verifiedEmailId(request.getEmailId() != null ? request.getEmailId() : existingTenant.getVerifiedEmailId())
                .gender(request.getGender() != null ? request.getGender() : existingTenant.getGender())
                .dateOfBirth(request.getDateOfBirth() != null ? request.getDateOfBirth() : existingTenant.getDateOfBirth())
                .build();
    }

    public static StoredUser create(CreateUserRequest request) {
        return StoredUser.builder()
                .type(request.getType())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .state(request.getState())
                .countryCode(request.getCountryCode())
                .primaryPhoneNumber(request.getPhoneNumber())
                .verifiedEmailId(request.getEmailId())
                .gender(request.getGender())
                .build();
    }

}
