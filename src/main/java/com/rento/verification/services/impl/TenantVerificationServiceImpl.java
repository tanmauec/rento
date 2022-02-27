package com.rento.verification.services.impl;

import com.google.common.collect.ImmutableMap;
import com.rento.core.exceptions.ErrorCode;
import com.rento.core.exceptions.RentoServiceException;
import com.rento.core.external.accountaggregator.models.ConsentResponse;
import com.rento.core.external.accountaggregator.models.DataSessionCreateRequest;
import com.rento.core.external.accountaggregator.models.DataSessionCreateResponse;
import com.rento.verification.models.*;
import com.rento.core.external.accountaggregator.services.AccountAggregatorService;
import com.rento.core.external.accountaggregator.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantVerificationServiceImpl implements TenantVerificationService {

    @Autowired
    private AccountAggregatorService aggregatorService;

    public TenantConsentInitResponse consentInit(TenantConsentInitRequest request) {
        try {
            ConsentResponse consentResponse = aggregatorService.createConsentRequest(ConverterUtil.toConsentRequest(
                    request.getPhoneNumber(), request.getRedirectUrl()));
            return TenantConsentInitResponse.builder()
                    .consentResponse(consentResponse)
                    .build();
        } catch (Exception ex) {
            throw RentoServiceException.error(ErrorCode.INTERNAL_ERROR,
                    ImmutableMap.of("Consent Init failed", ex.getLocalizedMessage()));
        }
    }

    @Override
    public FetchTenantDataInitResponse fetchData(FetchTenantDataInitRequest request) {
        try {
            DataSessionCreateResponse sessionCreateResponse = aggregatorService.createDataSession(
                    DataSessionCreateRequest.builder()
                            .consentId(request.getConsentId())
                            .build()
            );
            return FetchTenantDataInitResponse.builder()
                    .sessionCreateResponse(sessionCreateResponse)
                    .build();
        } catch (Exception ex) {
            throw RentoServiceException.error(ErrorCode.INTERNAL_ERROR,
                    ImmutableMap.of("Consent Init failed", ""));
        }
    }
}
