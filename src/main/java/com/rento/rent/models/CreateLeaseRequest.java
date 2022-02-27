package com.rento.rent.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLeaseRequest {
    @NotBlank
    private String propertyId;
    @NotBlank
    private String ownerId;
    @Min(1) @Max(10)
    private int tenantCount;
    @NotEmpty
    private List<String> tenantIds;
    private String managerId;
    @Min(1)
    private int rentAmount;
    @Min(1)
    private int depositAmount;
    @Min(1)
    private int maintenanceAmount;
    @Min(1) @Max(31)
    private int rentPayDayOfMonth;
    @Min(1) @Max(36)
    private int rentDurationInMonths;
    private Date agreementFrom;
    private Date agreementTill;
}
