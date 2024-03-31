package com.internship.portal.nutrition.repository;

import com.internship.portal.nutrition.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByIdProfessionalAndTime(String idProfessional, Date time);

    @Query("select a from Appointment a where a.idUser = :idUser and a.time >= :from and a.time <= :to")
    List<Appointment> findAllPatientAppointmentsDhRegistroBetween(@Param("from") Date from, @Param("to") Date to, @Param("idUser") String idUser);

    @Query("select a from Appointment a where a.idProfessional = :professionalId and a.time >= :from and a.time <= :to")
    List<Appointment> findAllProfessionalAppointmentsDhRegistroBetween(@Param("from") Date from, @Param("to") Date to, @Param("professionalId") String professionalId);
}
