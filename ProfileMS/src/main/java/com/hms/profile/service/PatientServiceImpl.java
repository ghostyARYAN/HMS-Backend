package main.java.com.hms.profile.service;

import org.springframework.stereotype.Service;
import com.hms.profile.dto.PatientDTO;
import com.hms.profile.exception.HmsException;
import com.hms.profile.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.hms.profile.entity.Patient;
import java.util.Optional;
import com.hms.profile.entity.BloodGroup;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;


@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Long addPatient(main.java.com.hms.profile.dto.PatientDTO patientDTO) throws HmsException {
        if(patientDTO.getEmail()!=null && patientRepository.findByEmail(patientDTO.getEmail()).isPresent()) throw new HmsException("PATIENT_ALREADY_EXISTS");
        if(patientDTO.getAadharNo()!=null && patientRepository.findByAadharNo(patientDTO.getAadharNo()).isPresent()) throw new HmsException("PATIENT_ALREADY_EXISTS");
        return patientRepository.save(patientDTO.toEntity());
    }

    @Override
    public PatientDTO getPatientById(Long id) throws HmsException {
        return patientRepository.findById(id).orElseThrow(()->new HmsException("PATIENT_NOT_FOUND")).toDTO();
    }
    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) throws HmsException {
       patientRepository.findById(patientDTO.getId()).orElseThrow(() -> new HmsException("PATIENT_NOT_FOUND"));
        return patientRepository.save(patientDTO.toEntity()).toDTO();
    }   
    @Override
    public Boolean patientExists(Long id) throws HmsException {
        return patientRepository.existsById(id);
    }
}
