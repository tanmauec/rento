package com.rento.core.external.accountaggregator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataSessionCreateResponse {
    private DataFetchStatus status;
    private String format;

    private String consentId;

    //data session id
    private String id;

    @JsonProperty("DataRange")
    private DataRange DataRange;
}
