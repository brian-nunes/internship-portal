package com.internship.portal.ticket.controller;

import com.internship.portal.microservices.commons.model.Session;
import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.ticket.dto.MessageDTO;
import com.internship.portal.ticket.dto.TicketDTO;
import com.internship.portal.ticket.dto.SuccessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import com.internship.portal.ticket.service.TicketService;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<SuccessDTO> postTicket(@RequestBody TicketDTO ticketDTO) {
        ticketService.postTicket(ticketDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getTickets() {
        List<TicketDTO> emotions = ticketService.getTickets();
        return new ResponseEntity<>(emotions, HttpStatus.OK);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<SuccessDTO> pucharseTicket(@PathVariable("ticketId") Long ticketId) {
        ticketService.pucharseTicket(ticketId);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }
}