package com.rento.rent.repository;

import com.rento.rent.storage.StoredRentPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentPaymentRepository extends JpaRepository<StoredRentPayments, Long> {

    @Query(value = "select * from payments where tenant_id=:tenantId", nativeQuery = true)
    List<StoredRentPayments> getPaymentsByTenantId(@Param("tenantId") String tenantId);
}
