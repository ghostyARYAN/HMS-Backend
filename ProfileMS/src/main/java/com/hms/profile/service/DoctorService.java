package main.java.com.hms.profile.service;

import java.util.List;
import main.java.com.hms.profile.dto.DoctorDropdown;

import main.java.com.hms.profile.dto.DoctorDTO;
import com.hms.profile.exception.HmsException;

public interface DoctorService {
    public Long addDoctor(DoctorDTO doctorDTO) throws HmsException;
    public DoctorDTO getDoctorById(Long id) throws HmsException;
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) throws HmsException;
    public Boolean doctorExists(Long id) throws HmsException;
    public List<DoctorDropdown> getDoctorDropdown() throws HmsException;
}