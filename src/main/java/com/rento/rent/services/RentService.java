package com.rento.rent.services;

import com.rento.rent.models.CreateLeaseRequest;
import com.rento.rent.models.LeaseDetails;
import com.rento.rent.models.RentPayment;
import com.rento.rent.models.RentPaymentRequest;

import java.util.List;

public interface RentService {
    LeaseDetails createLeaseDetails(CreateLeaseRequest request);

    boolean acceptRentTerms(String leaseId, String tenantId);

    boolean payRent(RentPaymentRequest rentPaymentRequest);

    List<RentPayment> getAllPayments(String tenantId);
}
