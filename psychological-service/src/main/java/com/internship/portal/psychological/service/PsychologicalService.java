package com.internship.portal.psychological.service;

import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import com.internship.portal.psychological.dto.MedicineDTO;
import com.internship.portal.psychological.dto.MedicineListDTO;
import com.internship.portal.psychological.model.Medicine;
import com.internship.portal.psychological.repository.MedicineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PsychologicalService {
    @Autowired
    private MedicineRepository medicineRepository;

    public Medicine postMedicine(MedicineDTO medicineDTO){
        return medicineRepository.save(new Medicine(medicineDTO));
    }

    public Medicine putMedicine(MedicineDTO medicineDTO){
        Medicine Medicine = medicineRepository.findById(medicineDTO.getId()).orElseThrow(() -> new BaseBusinessException("MEDICINE_NOT_FOUND", "No medicine with given id", HttpStatus.FORBIDDEN));
        Medicine.setName(medicineDTO.getName());
        Medicine.setIdUser(medicineDTO.getIdUser());
        Medicine.setCreatedAt(medicineDTO.getCreatedAt());
        Medicine.setInstructions(medicineDTO.getInstructions());
        return medicineRepository.save(Medicine);
    }

    public MedicineListDTO getMedicineList(String userDocument){
        List<Medicine> medicineList = medicineRepository.findByIdUser(userDocument);
        List<MedicineDTO> medicineDTOS = new ArrayList<>();
        medicineList.forEach(medicine -> medicineDTOS.add(new MedicineDTO(medicine)));
        return MedicineListDTO.builder().medicineList(medicineDTOS).build();
    }
}
