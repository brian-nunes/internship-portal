package com.internship.portal.professional.service;

import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import com.internship.portal.professional.dto.NoteDTO;
import com.internship.portal.professional.model.Nota;
import com.internship.portal.professional.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProfessionalService {
    @Autowired
    private NoteRepository noteRepository;

    public Nota postNote(NoteDTO noteDTO, String professionalDocument){
        noteDTO.setIdProfessional(professionalDocument);
        return noteRepository.save(new Nota(noteDTO));
    }

    public NoteDTO getNote(String professionalDocument, String patientDocument){
        return new NoteDTO(noteRepository.findByIdUserAndIsProfessional(professionalDocument, patientDocument).orElseThrow(() -> new BaseBusinessException("NOTE_NOT_FOUND", "Nota nao encontrada", HttpStatus.FORBIDDEN)));
    }

    public Nota putNote(NoteDTO noteDTO, String professionalDocument){
        noteDTO.setIdProfessional(professionalDocument);
        Nota nota = noteRepository.findByIdUserAndIsProfessional(noteDTO.getIdProfessional(), noteDTO.getIdUser()).orElseThrow(() -> new BaseBusinessException("NOTE_NOT_FOUND", "Nota nao encontrada", HttpStatus.FORBIDDEN));
        nota.setNota(noteDTO.getNota());
        return noteRepository.save(nota);
    }


}
