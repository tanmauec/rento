package com.rento.core.external.accountaggregator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsentDetail {
    private String consentStart;
    private String consentExpiry;
    private ConsentMode consentMode;
    private FetchType fetchType;
    private Set<ConsentType> consentTypes;
    @JsonProperty("FIDataRange")
    private DataRange FIDataRange;
    @JsonProperty("DataLife")
    private DataLife DataLife;
    @JsonProperty("Frequency")
    private DataFetchFrequency Frequency;
    private Set<FIType> fiTypes;
    @JsonProperty("DataConsumer")
    private DataConsumer DataConsumer;
    @JsonProperty("Customer")
    private Customer Customer;
    @JsonProperty("Purpose")
    private Purpose Purpose;
}
