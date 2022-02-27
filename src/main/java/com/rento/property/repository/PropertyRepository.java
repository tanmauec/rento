package com.rento.property.repository;

import com.rento.property.storage.StoredProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropertyRepository extends JpaRepository<StoredProperty, Long> {

    @Query(value = "select * from users where property_id=:propertyId", nativeQuery = true)
    StoredProperty getUserById(@Param("propertyId") String userId);

}
