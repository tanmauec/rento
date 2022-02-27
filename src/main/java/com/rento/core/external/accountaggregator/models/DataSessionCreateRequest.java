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
public class DataSessionCreateRequest {
    private String consentId;

    @JsonProperty("DataRange")
    private DataRange DataRange;

    @Builder.Default
    private String format = "json";
}
