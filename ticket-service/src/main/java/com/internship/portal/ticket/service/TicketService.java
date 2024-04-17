package com.internship.portal.ticket.service;

import com.internship.portal.ticket.dto.MessageDTO;
import com.internship.portal.ticket.dto.TicketDTO;
import com.internship.portal.ticket.model.Ticket;
import com.internship.portal.ticket.repository.TicketRepository;
import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    //@Autowired
    //private SimpMessagingTemplate messagingTemplate;

    public Ticket postTicket(TicketDTO ticketDTO){
        return ticketRepository.save(new Ticket(ticketDTO));
    }

    public Ticket pucharseTicket(Long ticketId){
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new BaseBusinessException("TICKET_NOT_FOUND", "Ticket nao encontrado", HttpStatus.FORBIDDEN));
        if(ticket.isPucharsed()){
            throw new BaseBusinessException("TICKET_ALREADY_BOUGHT", "Ticket ja comprado", HttpStatus.FORBIDDEN);
        }
        ticket.setPucharsed(true);
        //messagingTemplate.convertAndSend("/topic/messages", new MessageDTO(ticketId));
        return ticketRepository.save(ticket);
    }

    public List<TicketDTO> getTickets(){
        List<TicketDTO> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(ticket -> {
            tickets.add(new TicketDTO(ticket));
        });
        return tickets;
    }
}
