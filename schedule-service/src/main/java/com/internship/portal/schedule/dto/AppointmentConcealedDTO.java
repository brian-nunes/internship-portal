package com.internship.portal.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.schedule.model.Appointment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentConcealedDTO {

    @JsonProperty(value = "time")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date time;

    public AppointmentConcealedDTO(Appointment appointment) {
        this.time = appointment.getTime();
    }
}
