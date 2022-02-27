package com.rento.users.storage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rento.users.models.Gender;
import com.rento.users.models.State;
import com.rento.users.models.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        ignoreUnknown = true,
        value = {"handler", "hibernate_lazy_initializer"})
public class StoredUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, insertable = false, updatable = false)
    private int id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "verified_email_id")
    private String verifiedEmailId;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "primary_phone_number")
    private String primaryPhoneNumber;

    //primary phonenumber and password are used as login creds together.
    @Column(name = "password")
    private String password;

    /**
     * Adding for redundancy, since notifications can be High Priority.
     */
    @Column(name = "secondary_phone_number")
    private String secondaryPhoneNumber;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "created", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated", insertable = false, updatable = false)
    private Date updatedAt;
}
