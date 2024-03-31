package com.internship.portal.nutrition.service;

import com.internship.portal.nutrition.dto.AppointmentDTO;
import com.internship.portal.nutrition.model.Appointment;
import com.internship.portal.schedule.dto.AppointmentConcealedDTO;
import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import com.internship.portal.schedule.dto.NewAppointmentDTO;
import com.internship.portal.schedule.dto.ScheduleBlockDTO;
import com.internship.portal.nutrition.repository.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ScheduleService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment postAppointment(NewAppointmentDTO newAppointmentDTO, String userDocument){
        if(appointmentRepository.findByIdProfessionalAndTime(newAppointmentDTO.getIdProfessional(), newAppointmentDTO.getTime()).isPresent()){
            throw new BaseBusinessException("SCHEDULE_NOT_FREE", "Professional isnt free at the given date.", HttpStatus.UNAUTHORIZED);
        }
        Appointment appointment = new Appointment(newAppointmentDTO.getIdProfessional(), userDocument, newAppointmentDTO.getTime());
        return appointmentRepository.save(appointment);
    }

    public boolean cancelAppointment(String userDocument, Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new BaseBusinessException("APPOINTMENT_NOT_FOUND", "Theres no appointment with given Id.", HttpStatus.BAD_REQUEST));
        if (appointment.getIdUser() != userDocument){
            throw new BaseBusinessException("APPOINTMENT_NOT_OWNED", "Given appointment isnt for the given user.", HttpStatus.FORBIDDEN);
        }
        appointmentRepository.delete(appointment);
        return true;
    }

    public AppointmentDTO getAppointment(Long appointmentId){
        return new AppointmentDTO(appointmentRepository.findById(appointmentId).orElseThrow(() -> new BaseBusinessException("APPOINTMENT_NOT_FOUND", "Appointment not found", HttpStatus.BAD_REQUEST)));
    }

    public List<AppointmentDTO> getProfessionalAppointments(String fromString, String toString, String userDocument){
        List<Appointment> appointments = appointmentRepository.findAllProfessionalAppointmentsDhRegistroBetween(stringToDate(fromString), stringToDate(toString), userDocument);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        appointments.forEach(appointment -> appointmentDTOS.add(new AppointmentDTO(appointment)));
        return appointmentDTOS;
    }

    public List<AppointmentDTO> getPatientAppointments(String fromString, String toString, String userDocument){
        List<Appointment> appointments = appointmentRepository.findAllPatientAppointmentsDhRegistroBetween(stringToDate(fromString), stringToDate(toString), userDocument);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        appointments.forEach(appointment -> appointmentDTOS.add(new AppointmentDTO(appointment)));
        return appointmentDTOS;
    }

    public List<AppointmentConcealedDTO> getProfessionalSchedule(String fromString, String toString, String professionalId){
        List<Appointment> appointments = appointmentRepository.findAllProfessionalAppointmentsDhRegistroBetween(stringToDate(fromString), stringToDate(toString), professionalId);
        List<AppointmentConcealedDTO> appointmentConcealedDTOS = new ArrayList<>();
        appointments.forEach(appointment -> appointmentConcealedDTOS.add(new AppointmentConcealedDTO(appointment)));
        return appointmentConcealedDTOS;
    }

    public Appointment blockSchedule(String professionalId, ScheduleBlockDTO scheduleBlockDTO){
        if(appointmentRepository.findByIdProfessionalAndTime(professionalId, scheduleBlockDTO.getTime()).isPresent()){
            throw new BaseBusinessException("SCHEDULE_NOT_FREE", "Professional isnt free at the given date.", HttpStatus.UNAUTHORIZED);
        }
        Appointment appointment = new Appointment(professionalId, scheduleBlockDTO.getTime());
        return appointmentRepository.save(appointment);
    }

    public boolean unblockSchedule(String professionalId, ScheduleBlockDTO scheduleBlockDTO){
        Appointment appointment = appointmentRepository.findByIdProfessionalAndTime(professionalId, scheduleBlockDTO.getTime()).orElseThrow(() -> new BaseBusinessException("APPOINTMENT_NOT_FOUND", "Appointment not found", HttpStatus.BAD_REQUEST));
        if(appointment.getIdUser() != null){
            throw new BaseBusinessException("APPOINTMENT_WITH_PATIENT", "This is not a blocked schedule, theres a patient to attend.", HttpStatus.UNAUTHORIZED);
        }
        appointmentRepository.delete(appointment);
        return true;
    }

    private Date stringToDate(String stringDate){
        try {
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(stringDate);
        } catch (ParseException e) {
            throw new BaseBusinessException("DATE_FORMAT_EXCEPTION", "Incorrect date format, please use dd-MM-yyyy", HttpStatus.BAD_REQUEST);
        }
    }
}
