package com.rento.core.external.accountaggregator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsentResponse {
    private String id;
    private String url;
    private ConsentStatus status;
    private ConsentUsage usage;
    @JsonProperty("Detail")
    private ConsentDetail Detail;
}
