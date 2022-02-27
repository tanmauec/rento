package com.rento.core.external.accountaggregator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataFetchFrequency {
    private int value;
    private TimeUnit unit;
}
