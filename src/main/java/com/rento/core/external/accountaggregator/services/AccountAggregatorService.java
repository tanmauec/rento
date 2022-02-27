package com.rento.core.external.accountaggregator.services;

import com.rento.core.external.accountaggregator.models.ConsentRequest;
import com.rento.core.external.accountaggregator.models.ConsentResponse;
import com.rento.core.external.accountaggregator.models.DataSessionCreateRequest;
import com.rento.core.external.accountaggregator.models.DataSessionCreateResponse;

public interface AccountAggregatorService {

    ConsentResponse createConsentRequest(ConsentRequest consentRequest) throws Exception;

    DataSessionCreateResponse createDataSession(DataSessionCreateRequest dataSessionCreateRequest) throws Exception;

}
