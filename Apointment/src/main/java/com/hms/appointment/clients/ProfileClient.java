package main.java.com.hms.appointment.clients;

import org.springframework.cloud.openfeign.FeignClient;

import com.hms.appointment.dto.DoctorDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import main.java.com.hms.appointment.dto.PatientDTO;

@FeignClient(name = "ProfileMS", configuration = FeignConfig.class)
public interface ProfileClient {
    
    @GetMapping("/profile/doctor/exists/{id}")
    Boolean doctorExists(@PathVariable("id") Long Id); 

    @GetMapping("/profile/patient/exists/{id}")
    Boolean patientExists(@PathVariable("id") Long Id);

    @GetMapping("/profile/patient/get/{id}")
    PatientDTO getPatientById(@PathVariable("id") Long id);

    @GetMapping("/profile/doctor/get/{id}") 
    DoctorDTO getDoctorById(@PathVariable("id") Long id);
}
