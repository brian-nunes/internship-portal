package com.internship.portal.schedule.controller;

import com.internship.portal.microservices.commons.model.Session;
import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.schedule.dto.*;
import com.internship.portal.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable("appointmentId") Long appointmentId) {
        AppointmentDTO appointmentDTO = scheduleService.getAppointment(appointmentId);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessDTO> postAppointment(@RequestHeader("SessionData") String sessionDataHeader, @RequestBody NewAppointmentDTO newAppointmentDTO) {
        Session session = sessionService.decodeSessionData(sessionDataHeader);
        scheduleService.postAppointment(newAppointmentDTO, session.getDocumentNumber());
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<SuccessDTO> deleteAppointment(@RequestHeader("SessionData") String sessionDataHeader, @PathVariable Long appointmentId) {
        Session session = sessionService.decodeSessionData(sessionDataHeader);
        scheduleService.cancelAppointment(session.getDocumentNumber(), appointmentId);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @GetMapping("/professional/appointment")
    public ResponseEntity<List<AppointmentDTO>> getProfessionalAppointments(@RequestHeader("SessionData") String sessionDataHeader, @RequestParam(value = "from", required = false, defaultValue = "01-01-1970") String from, @RequestParam(value = "to", required = false, defaultValue = "31-12-2100") String to) {
        Session session = sessionService.decodeSessionData(sessionDataHeader);
        List<AppointmentDTO> appointments = scheduleService.getProfessionalAppointments(from, to, session.getDocumentNumber());
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/patient/appointment")
    public ResponseEntity<List<AppointmentDTO>> getPatientAppointments(@RequestHeader("SessionData") String sessionDataHeader, @RequestParam(value = "from", required = false, defaultValue = "01-01-1970") String from, @RequestParam(value = "to", required = false, defaultValue = "31-12-2100") String to) {
        Session session = sessionService.decodeSessionData(sessionDataHeader);
        List<AppointmentDTO> appointments = scheduleService.getPatientAppointments(from, to, session.getDocumentNumber());
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<AppointmentConcealedDTO>> getProfessionalSchedule(@PathVariable("professionalId") String professionalId, @RequestParam(value = "from", required = false, defaultValue = "01-01-1970") String from, @RequestParam(value = "to", required = false, defaultValue = "31-12-2100") String to) {
        List<AppointmentConcealedDTO> appointments = scheduleService.getProfessionalSchedule(from, to, professionalId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }


    @PostMapping("/professional/block")
    public ResponseEntity<SuccessDTO> blockSchedule(@RequestHeader("SessionData") String sessionDataHeader, @RequestBody ScheduleBlockDTO scheduleBlockDTO) {
        Session session = sessionService.decodeSessionData(sessionDataHeader);
        scheduleService.blockSchedule(session.getDocumentNumber(), scheduleBlockDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @PostMapping("/professional/unblock")
    public ResponseEntity<SuccessDTO> unblockSchedule(@RequestHeader("SessionData") String sessionDataHeader, @RequestBody ScheduleBlockDTO scheduleBlockDTO) {
        Session session = sessionService.decodeSessionData(sessionDataHeader);
        scheduleService.unblockSchedule(session.getDocumentNumber(), scheduleBlockDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

}