package com.rento.core.external.accountaggregator.services;

import com.rento.core.external.accountaggregator.models.*;
import com.rento.core.utils.SerDe;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static com.rento.configuration.AppConfig.client;
import static com.rento.core.constants.Constants.CONTENT_TYPE_JSON;

@Service
public class AccountAggregatorServiceImpl implements AccountAggregatorService {

    private static final String CLIENT_ID_HEADER = "x-client-id";
    private static final String CLIENT_SECRET_HEADER = "x-client-secret";
    private final String SETU_BASE_PATH = "https://fiu-uat.setu.co";
    private final String clientId = "41eef3d9-7450-4aae-b5cd-635e9b50077a";
    private final String clientSecret = "b5a50f9f-28b5-4887-b0bf-def9be77c947";

    public ConsentResponse createConsentRequest(ConsentRequest consentRequest) throws Exception {
        RequestBody body = RequestBody.create(SerDe.writeValueAsString(consentRequest), MediaType.parse(CONTENT_TYPE_JSON));
        Request request = new Request.Builder()
                .url(SETU_BASE_PATH + "/consents")
                .addHeader(CLIENT_ID_HEADER, clientId)
                .addHeader(CLIENT_SECRET_HEADER, clientSecret)
                .method("POST", body)
                .build();
//        System.out.println(SerDe.writeValueAsString(request));
        Response response = client.newCall(request).execute();
//        System.out.println(SerDe.writeValueAsString(response));
//        System.out.println("Consent init: " + response.body().string());
        return SerDe.readValue(response.body().string(), ConsentResponse.class);
    }

    public ConsentResponse getConsentStatus(String consentId) throws Exception {
        Request request = new Request.Builder()
                .url(SETU_BASE_PATH + "/consents/" + consentId)
                .addHeader(CLIENT_ID_HEADER, clientId)
                .addHeader(CLIENT_SECRET_HEADER, clientSecret)
                .get()
                .build();
        Response response = client.newCall(request).execute();
//        System.out.println("consent status:" + response.body().string());
        return SerDe.readValue(response.body().string(), ConsentResponse.class);
    }

    @Override
    public DataSessionCreateResponse createDataSession(DataSessionCreateRequest dataSessionCreateRequest) throws Exception {
        DataRange consentDataRange = getConsentStatus(dataSessionCreateRequest.getConsentId()).getDetail().getFIDataRange();
        dataSessionCreateRequest.setDataRange(DataRange.builder()
                .from(consentDataRange.getFrom())
                .to(consentDataRange.getTo())
                .build());

        RequestBody body = RequestBody.create(SerDe.writeValueAsString(dataSessionCreateRequest), MediaType.parse(CONTENT_TYPE_JSON));
        System.out.println(SerDe.writeValueAsString(dataSessionCreateRequest));
        Request request = new Request.Builder()
                .url(SETU_BASE_PATH + "/sessions")
                .addHeader(CLIENT_ID_HEADER, clientId)
                .addHeader(CLIENT_SECRET_HEADER, clientSecret)
                .method("POST", body)
                .build();

        Response response = client.newCall(request).execute();
        //System.out.println( "session create: " + response.body().string());
        return SerDe.readValue(response.body().string(), DataSessionCreateResponse.class);
    }
}
