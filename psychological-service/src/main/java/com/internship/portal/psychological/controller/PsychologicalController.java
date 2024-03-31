package com.internship.portal.psychological.controller;

import com.internship.portal.microservices.commons.model.Session;
import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.psychological.dto.MedicineDTO;
import com.internship.portal.psychological.dto.MedicineListDTO;
import com.internship.portal.psychological.dto.SuccessDTO;
import com.internship.portal.psychological.service.PsychologicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/psychological")
public class PsychologicalController {

    @Autowired
    private PsychologicalService psychologicalService;

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SuccessDTO> postMedicine( @RequestBody MedicineDTO medicineDTO) {
        psychologicalService.postMedicine(medicineDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SuccessDTO> putMedicine(@RequestBody MedicineDTO medicineDTO) {
        psychologicalService.putMedicine(medicineDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @GetMapping("/{userDocument}")
    public ResponseEntity<MedicineListDTO> getMedicineListByProfessional(@PathVariable("userDocument") String userDocument) {
        MedicineListDTO medicineListDTO = psychologicalService.getMedicineList(userDocument);
        return new ResponseEntity<>(medicineListDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<MedicineListDTO> getMedicineListByUser(@RequestHeader("SessionData") String sessionData) {
        Session session = sessionService.decodeSessionData(sessionData);
        MedicineListDTO medicineListDTO = psychologicalService.getMedicineList(session.getDocumentNumber());
        return new ResponseEntity<>(medicineListDTO, HttpStatus.OK);
    }
}