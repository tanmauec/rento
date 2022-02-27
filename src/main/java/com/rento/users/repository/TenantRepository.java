package com.rento.users.repository;

import com.rento.users.storage.StoredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TenantRepository extends JpaRepository<StoredUser, Long> {

    @Query(value = "select * from users where country_code=:countryCode and primary_phone_number=:phoneNumber", nativeQuery = true)
    StoredUser getUserByPhoneNumber(@Param("countryCode") String countryCode,
                                    @Param("phoneNumber") String phoneNumber);

    @Query(value = "select * from users where email_id=:emailId", nativeQuery = true)
    StoredUser getUserByEmailId(@Param("emailId") String emailId);

    @Query(value = "select * from users where user_id=:userId", nativeQuery = true)
    StoredUser getUserById(@Param("userId") String userId);

    @Query(value = "select * from users where user_id in :userIds", nativeQuery = true)
    List<StoredUser> getUsers(@Param("userIds") List<String> userIds);

}