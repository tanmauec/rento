package com.rento.property.repository;

import com.rento.property.storage.StoredProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<StoredProperty, Long> {

    @Query(value = "select * from property where property_id=:propertyId", nativeQuery = true)
    StoredProperty getPropertyById(@Param("propertyId") String propertyId);

    @Query(value = "select * from property where owner_id=:ownerId", nativeQuery = true)
    List<StoredProperty> getProperties(@Param("ownerId") String ownerId);
}
