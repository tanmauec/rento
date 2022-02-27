package com.rento.core.external.accountaggregator.utils;

import com.google.common.collect.Sets;
import com.rento.core.external.accountaggregator.models.*;
import com.rento.core.utils.DateUtils;
import com.rento.core.utils.SerDe;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static com.rento.core.external.accountaggregator.models.ConsentType.*;
import static com.rento.core.external.accountaggregator.models.FIType.DEPOSIT;
import static com.rento.core.external.accountaggregator.models.FIType.TERM_DEPOSIT;
import static com.rento.core.utils.DateUtils.*;

public class ConverterUtil {

    public static ConsentRequest toConsentRequest(String customerPhoneNumber,
                                                  String redirectUrl) {

        ConsentRequest consentRequest = ConsentRequest.builder()
                .Detail(ConsentDetail.builder()
                        .consentStart(DateTimeFormatter.ISO_INSTANT.format(ZonedDateTime.now(IST_ZONE_ID)))
                        .consentExpiry(DateTimeFormatter.ISO_INSTANT.format(ZonedDateTime.now(IST_ZONE_ID).plus(Duration.of(30, ChronoUnit.DAYS))))
                        .consentMode(ConsentMode.STORE)
                        .Customer(Customer.builder().id(String.format("%s@onemoney", customerPhoneNumber)).build())
                        .consentTypes(Sets.newHashSet(PROFILE, TRANSACTIONS, SUMMARY))
                        .fetchType(FetchType.PERIODIC)
                        .DataConsumer(DataConsumer.builder()
                                .id("setu-fiu-id")
                                .build())
                        .Purpose(Purpose.builder()
                                .code(PurposeCode.WEALTH_MANAGEMENT.getCode())
                                .text("Rental verification")
                                .build())
                        .fiTypes(Sets.newHashSet(DEPOSIT, TERM_DEPOSIT))
                        .DataLife(DataLife.builder()
                                .value(1)
                                .unit(TimeUnit.DAY)
                                .build())
                        .Frequency(DataFetchFrequency.builder()
                                .unit(TimeUnit.MONTH)
                                .value(10)
                                .build())

                        .FIDataRange(DataRange.builder()
                                .from("2021-04-01T00:00:00.000Z")
                                .to("2021-10-01T00:00:00.000Z")
                                .build())
                        .build())
                .redirectUrl(redirectUrl)
                .build();
        System.out.println(SerDe.writeValueAsString(consentRequest));

        return consentRequest;
    }
}
