package com.rento.rent.storage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rento.rent.models.RentMonth;
import com.rento.users.models.Gender;
import com.rento.users.models.State;
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
@Entity(name = "payments")
@Table(name = "payments")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        ignoreUnknown = true,
        value = {"handler", "hibernate_lazy_initializer"})
public class StoredRentPayments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, insertable = false, updatable = false)
    private int id;

    @Column(name = "transaction_id", unique = true, nullable = false)
    private String transactionId;

    @Column(name = "property_id", unique = true, nullable = false)
    private String propertyId;

    @Column(name = "tenant_id", unique = true, nullable = false)
    private String tenantId;

    @Column(name = "rent_month")
    @Enumerated(EnumType.STRING)
    private RentMonth rentMonth;

    @Column(name = "amount")
    private int amount;
}
