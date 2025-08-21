package main.java.com.hms.profile.service;

import org.springframework.stereotype.Service;
import main.java.com.hms.profile.dto.DoctorDTO;
import main.java.com.hms.profile.repository.DoctorRepository;
import com.hms.profile.entity.Doctor;
import main.java.com.hms.profile.exception.HmsException;
import java.util.Optional;
import java.util.List;





import main.java.com.hms.profile.repository.DoctorRepository;
import com.hms.profile.exception.HmsException;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Long addDoctor(DoctorDTO doctorDTO) {
       if(doctorDTO.getEmail()!=null && doctorRepository.findByEmail(doctorDTO.getEmail()) .isPresent()) {
            throw new HmsException("DOCTOR_ALREADY_EXISTS");
            return doctorRepository.save(doctorDTO.toEntity()).getId();
        }
        if(doctorDTO.getLicenseNo()!=null && doctorRepository.findByLicenseNo(doctorDTO.getLicenseNo()) .isPresent()) {
            throw new HmsException("DOCTOR_ALREADY_EXISTS");
            return doctorRepository.save(doctorDTO.toEntity()).getId();
        }
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
       return doctorRepository.findById(id)
                .orElseThrow(() -> new HmsException("DOCTOR_NOT_FOUND"))
                .toDTO();
    }
    @Override
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(doctorDTO.getId());
        if (!existingDoctor.isPresent()) {
            throw new HmsException("DOCTOR_NOT_FOUND");
        }
        Doctor doctor = existingDoctor.get();
        doctor.updateFromDTO(doctorDTO);
        return doctorRepository.save(doctor).toDTO();
    }
    @Override
    public Boolean doctorExists(Long id) {
        return doctorRepository.existsById(id);
    }
    @Override
    public List<DoctorDropdown> getDoctorDropdown() {
        return doctorRepository.findAllDoctorDropdown();
    }
    
}
