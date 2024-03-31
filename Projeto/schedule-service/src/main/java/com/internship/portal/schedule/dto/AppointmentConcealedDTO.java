package com.internship.portal.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.schedule.model.Appointment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentConceiledDTO {

    @JsonProperty(value = "time")
    private Date time;
    
    public AppointmentConceiledDTO(Appointment appointment) {
        this.time = appointment.getTime();
    }
}
