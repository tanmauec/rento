package com.rento.rent.storage;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rento.users.models.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "leases")
@Table(name = "leases")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        ignoreUnknown = true,
        value = {"handler", "hibernate_lazy_initializer"})
public class StoredLease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, insertable = false, updatable = false)
    private int id;

    @Column(name = "lease_id", unique = true, nullable = false)
    private String leaseId;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "property_id", nullable = false)
    private String propertyId;

    @Column(name = "tenant_count", nullable = false)
    private int tenantCount;

    @Column(name = "tenants", nullable = false)
    private String tenants;

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "monthly_rent", nullable = false)
    private int rentAmount;

    @Column(name = "deposit_amount", nullable = false)
    private int depositAmount;

    @Column(name = "maintenance_amount", nullable = false)
    private int maintenanceAmount;

    @Column(name = "payment_day_of_month", nullable = false)
    private int rentPayDayOfMonth;

    @Column(name = "lease_duration_months", nullable = false)
    private int rentDurationInMonths;

    @Column(name = "lease_start")
    private Date startDate;

    @Column(name = "lease_end")
    private Date endDate;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "created", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated", insertable = false, updatable = false)
    private Date updatedAt;
}
