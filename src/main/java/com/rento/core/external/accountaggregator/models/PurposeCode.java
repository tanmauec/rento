package com.rento.core.external.accountaggregator.models;

import lombok.Getter;

@Getter
public enum PurposeCode {
    WEALTH_MANAGEMENT("101")
    ;

    private String code;

    private PurposeCode(String code) {
        this.code = code;
    }
}
