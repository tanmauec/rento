package com.rento.rent.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentPayment {
    private String propertyId;
    private String tenantId;
    private int amount;
    private RentMonth paymentForMonth;
}
