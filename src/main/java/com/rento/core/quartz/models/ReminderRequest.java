package com.rento.core.quartz.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.quartz.JobDetail;

@Data
@Builder
public class ReminderRequest {

    @JsonProperty(value = "job_detail", required = true)
    private JobDetail jobDetail;

    @JsonProperty(value = "job_class", required = true)
    private String jobClass;

    @JsonProperty(value = "job_description", required = true)
    private String jobDescription;

    @JsonProperty(value = "epoch_seconds", required = true)
    private long epochSeconds;
}
