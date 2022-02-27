package com.rento.verification.models;

import com.rento.core.external.accountaggregator.models.DataSessionCreateResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FetchTenantDataInitResponse {
    private DataSessionCreateResponse sessionCreateResponse;
}
