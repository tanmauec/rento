package com.rento.core.quartz.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushNotificationRequest {

    private String title;
    private String message;
    private String topic;
    private String token;

}