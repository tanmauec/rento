package com.rento.users.services.impl;

import com.rento.core.constants.Constants;
import com.rento.core.utils.IdGenerator;
import com.rento.users.models.CreateUserRequest;
import com.rento.users.models.State;
import com.rento.users.models.User;
import com.rento.users.models.UserType;
import com.rento.users.repository.TenantRepository;
import com.rento.users.services.UserService;
import com.rento.users.storage.StoredUser;
import com.rento.users.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rento.core.constants.Constants.OWNER_ID_PREFIX;
import static com.rento.core.constants.Constants.TENANT_ID_PREFIX;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TenantRepository tenantRepository;

    public User createUser(CreateUserRequest request) {
        Optional<User> existingTenantOpt = getUserByPhoneNumber(request.getCountryCode(), request.getPhoneNumber());

        if (existingTenantOpt.isPresent())
            return existingTenantOpt.get();

        String userId = generateUserId(request);
        StoredUser storedUser = ConverterUtil.create(request);
        storedUser.setUserId(userId);
        storedUser.setState(State.ENABLED);
        return ConverterUtil.toDto(tenantRepository.save(storedUser));
    }

    public Optional<User> getUserById(String userId) {
        StoredUser userDetails = tenantRepository.getUserById(userId);
        return Optional.ofNullable(userDetails == null ? null : ConverterUtil.toDto(userDetails));
    }

    public Optional<User> getUserByPhoneNumber(String countryCode, String phoneNumber) {
        StoredUser storedUser = tenantRepository.getUserByPhoneNumber(countryCode, phoneNumber);
        return Optional.ofNullable(ConverterUtil.toDto(storedUser));
    }

    public List<User> getUsers(List<String> tenantIds) {
        if (CollectionUtils.isEmpty(tenantIds))
            return Collections.emptyList();

        List<StoredUser> usersFound = tenantRepository.getUsers(tenantIds);
        return usersFound.stream().map(ConverterUtil::toDto).collect(Collectors.toList());
    }


    public boolean isPhoneNumberNotExist(String countryCode, String phoneNumber) {
        if (Objects.isNull(countryCode))
            countryCode = Constants.INDIA_COUNTRY_CODE;

        StoredUser existingTenant = tenantRepository.getUserByPhoneNumber(countryCode, phoneNumber);
        return Objects.nonNull(existingTenant);
    }

    private String generateUserId(CreateUserRequest request) {
        return request.getType().equals(UserType.OWNER) ?
                IdGenerator.generateNumericId(OWNER_ID_PREFIX) : IdGenerator.generateNumericId(TENANT_ID_PREFIX);
    }
}
