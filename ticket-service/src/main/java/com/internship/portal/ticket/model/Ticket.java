package com.internship.portal.ticket.model;

import com.internship.portal.ticket.dto.TicketDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ticket")
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat", nullable = false)
    private String seat;


    @Column(name = "isPucharsed", nullable = false)
    private boolean isPucharsed;

    public Ticket(TicketDTO ticketDTO){
        this.id = ticketDTO.getId();
        this.seat = ticketDTO.getSeat();
        this.isPucharsed = ticketDTO.isPucharsed();
    }
}
