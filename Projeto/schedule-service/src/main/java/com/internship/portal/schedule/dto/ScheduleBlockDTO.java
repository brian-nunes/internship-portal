package com.internship.portal.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NewScheduleBlockDTO {

    @JsonProperty(value = "time")
    private Date time;

}
