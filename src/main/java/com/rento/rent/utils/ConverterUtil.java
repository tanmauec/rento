package com.rento.rent.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rento.core.utils.SerDe;
import com.rento.rent.models.CreateLeaseRequest;
import com.rento.rent.models.LeaseDetails;
import com.rento.rent.models.RentPayment;
import com.rento.rent.storage.StoredLease;
import com.rento.rent.storage.StoredRentPayments;
import com.rento.users.models.State;
import com.sun.xml.bind.v2.model.core.TypeRef;

import java.util.List;

public class ConverterUtil {

    public static StoredLease toDao(CreateLeaseRequest request) {
        return StoredLease.builder()
                .propertyId(request.getPropertyId())
                .ownerId(request.getOwnerId())
                .managerId(request.getManagerId())
                .rentAmount(request.getRentAmount())
                .depositAmount(request.getDepositAmount())
                .maintenanceAmount(request.getMaintenanceAmount())
                .rentDurationInMonths(request.getRentDurationInMonths())
                .tenantCount(request.getTenantCount())
                .tenants(SerDe.writeValueAsString(request.getTenantIds()))
                .startDate(request.getAgreementFrom())
                .endDate(request.getAgreementTill())
                .rentPayDayOfMonth(request.getRentPayDayOfMonth())
                .build();
    }

    public static LeaseDetails toDto(StoredLease storedLease) {
        return LeaseDetails.builder()
                .leaseId(storedLease.getLeaseId())
                .ownerId(storedLease.getOwnerId())
                .tenantCount(storedLease.getTenantCount())
                .tenants(SerDe.readValue(storedLease.getTenants(), new TypeReference<List<String>>(){}))
                .agreementFrom(storedLease.getStartDate())
                .agreementTill(storedLease.getEndDate())
                .managerId(storedLease.getManagerId())
                .depositAmount(storedLease.getDepositAmount())
                .maintenanceAmount(storedLease.getMaintenanceAmount())
                .rentAmount(storedLease.getRentAmount())
                .rentDurationInMonths(storedLease.getRentDurationInMonths())
                .rentPayDayOfMonth(storedLease.getRentPayDayOfMonth())
                .build();
    }

    public static RentPayment toDto(StoredRentPayments storedRentPayments) {
        return RentPayment.builder()
                .propertyId(storedRentPayments.getPropertyId())
                .tenantId(storedRentPayments.getTenantId())
                .amount(storedRentPayments.getAmount())
                .paymentForMonth(storedRentPayments.getRentMonth())
                .build();
    }
}
