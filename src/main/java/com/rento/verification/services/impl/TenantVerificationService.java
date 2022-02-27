package com.rento.verification.services.impl;

import com.rento.verification.models.FetchTenantDataInitRequest;
import com.rento.verification.models.FetchTenantDataInitResponse;
import com.rento.verification.models.TenantConsentInitRequest;
import com.rento.verification.models.TenantConsentInitResponse;

public interface TenantVerificationService {

    TenantConsentInitResponse consentInit(TenantConsentInitRequest request);

    FetchTenantDataInitResponse fetchData(FetchTenantDataInitRequest request);

}
