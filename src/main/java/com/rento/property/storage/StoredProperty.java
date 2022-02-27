package com.rento.property.storage;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rento.property.models.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "property")
@Table(name = "property")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        ignoreUnknown = true,
        value = {"handler", "hibernate_lazy_initializer"})
public class StoredProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, insertable = false, updatable = false)
    private int id;

    @Column(name = "property_id", unique = true, nullable = false)
    private String propertyId;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Column(name = "owner_id", unique = true, nullable = false)
    private String ownerId;

    @Column(name = "name", nullable = false)
    private String name;
}
