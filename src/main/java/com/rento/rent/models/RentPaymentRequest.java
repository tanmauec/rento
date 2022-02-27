package com.rento.rent.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentPaymentRequest {
    @NotBlank
    private String propertyId;

    @NotBlank
    private String tenantId;

    @Min(1)
    private int amount;

    @NotNull
    private RentMonth rentMonth;
}
