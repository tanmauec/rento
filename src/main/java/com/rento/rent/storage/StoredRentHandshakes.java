package com.rento.rent.storage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rento.rent.models.LeaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rent_handshakes")
@Table(name = "rent_handshakes")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        ignoreUnknown = true,
        value = {"handler", "hibernate_lazy_initializer"})
public class StoredRentHandshakes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, insertable = false, updatable = false)
    private int id;

    @Column(name = "lease_id", unique = true, nullable = false)
    private String leaseId;

    @Column(name = "tenant_id", unique = true, nullable = false)
    private String tenantId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaseStatus status;

    @Column(name = "tenant_comments")
    private String tenantComments;

    @Column(name = "owner_comments")
    private String ownerComments;

    @Column(name = "created", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated", insertable = false, updatable = false)
    private Date updatedAt;
}
