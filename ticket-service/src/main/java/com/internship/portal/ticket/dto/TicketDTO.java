package com.internship.portal.ticket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.ticket.model.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TicketDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "seat", required = true)
    private String seat;

    @JsonProperty(value = "isPucharsed")
    private boolean isPucharsed;

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.seat = ticket.getSeat();
        this.isPucharsed = ticket.isPucharsed();
    }
}
