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
public class NewAppointmentDTO {

    @JsonProperty(value = "id_professional")
    private String idProfessional;

    @JsonProperty(value = "time")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date time;

}
