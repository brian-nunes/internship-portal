package com.internship.portal.pucharse.controller;

import com.internship.portal.pucharse.dto.SuccessDTO;
import com.internship.portal.pucharse.service.PucharseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pucharse")
public class PucharseController {

    @Autowired
    private PucharseService pucharseService;

    @PostMapping("/{ticketId}")
    public ResponseEntity<SuccessDTO> buyTicket(@PathVariable("ticketId") Long ticketId) throws Exception {
        pucharseService.buyTicket(ticketId);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }
}