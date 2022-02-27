package com.rento.rent.repository;

import com.rento.rent.storage.StoredRentHandshakes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentHandshakeRepository extends JpaRepository<StoredRentHandshakes, Long> {
    @Query(nativeQuery = true, value = "select * from rent_handshakes where lease_id=:leaseId")
    List<StoredRentHandshakes> getHandshakesByLeaseId(@Param("leaseId") String leaseId);

    @Query(nativeQuery = true, value = "select * from rent_handshakes where tenant_id=:tenantId and lease_id=:leaseId")
    StoredRentHandshakes getHandshakeByLeaseIdTenantId(@Param("tenantId") String tenantId,
                                                       @Param("leaseId") String leaseId);
}
