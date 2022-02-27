package com.rento.rent.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaseDetails {
    private String leaseId;
    private String ownerId;
    private int tenantCount;
    private List<String> tenants;
    private String managerId;
    private double rentAmount;
    private double depositAmount;
    private double maintenanceAmount;
    private int rentPayDayOfMonth;
    private int rentDurationInMonths;
    private Date agreementFrom;
    private Date agreementTill;
}
