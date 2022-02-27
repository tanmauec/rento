package com.rento.rent.repository;

import com.rento.rent.storage.StoredLease;
import org.omg.CORBA.BAD_CONTEXT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentRepository extends JpaRepository<StoredLease, Long> {

    @Query(value = "select * from leases where lease_id=:leaseId", nativeQuery = true)
    StoredLease getLeaseByLeaseId(@Param("leaseId") String leaseId);

    @Query(value = "select * from leases where owner_id=:ownerId", nativeQuery = true)
    List<StoredLease> getLeases(@Param("ownerId") String ownerId);

    @Query(value = "select * from leases where tenant_id=:tenantId", nativeQuery = true)
    StoredLease getLeaseByTenantId(@Param("tenantId") String tenantId);

    @Query(value = "select * from leases where property_id=:propertyId", nativeQuery = true)
    StoredLease getLeaseBypropertyId(@Param("propertyId") String propertyId);
}
