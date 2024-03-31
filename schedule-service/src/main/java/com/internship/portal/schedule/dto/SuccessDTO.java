package com.internship.portal.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SuccessDTO {
    @JsonProperty("success")
    private Boolean success;
}