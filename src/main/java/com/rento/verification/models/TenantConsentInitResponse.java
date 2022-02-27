package com.rento.verification.models;

import com.rento.core.external.accountaggregator.models.ConsentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class TenantConsentInitResponse {
    private ConsentResponse consentResponse;
}
