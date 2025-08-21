package main.java.com.hms.appointment.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hms.appointment.dto.AppointmentDTO;
import com.hms.appointment.repository.AppointmentRepository;
import java.time.LocalTime;
import com.hms.appointment.dto.Status;
import com.hms.appointment.entity.Appointment;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.dto.AppointmentDTO;
import com.hms.appointment.utility.ErrorInfo;
import com.hms.profile.entity.Patient;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;

import main.java.com.hms.appointment.clients.ProfileClient;
import main.java.com.hms.appointment.dto.AppointmentDetails;
import main.java.com.hms.appointment.dto.PatientDTO;
import main.java.com.hms.appointment.service.ApiService;
import main.java.com.hms.profile.entity.Doctor;
import main.java.com.hms.appointment.dto.DoctorDTO;





@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private ApiService apiService;

    @Autowired
    private ProfileClient profileClient;

    @Override
    public Long scheduleAppointment(AppointmentDTO appointmentDTO) throws HmsException {
       Boolean doctorExists = profileClient.doctorExists(appointmentDTO.getDoctorId())
            .block();
        if (doctorExists == null || !doctorExists) {
            throw new HmsException("DOCTOR_NOT_FOUND");
        }
        Boolean patientExists = profileClient.patientExists(appointmentDTO.getPatientId())
            .block();
        if (patientExists == null || !patientExists) {
            throw new HmsException("PATIENT_NOT_FOUND");
        }
        appointmentDTO.setStatus(Status.SCHEDULED);
        return appointmentRepository.save(appointmentDTO.toEntity()).getId();
    }

    @Override
    public void cancelAppointment(Long appointmentId) throws HmsException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new HmsException("APPOINTMENT_NOT_FOUND"));
        if(appointment.getStatus().equals (Status.CANCELLED)) {
            throw new HmsException("APPOINTMENT_ALREADY_CANCELLED");
        } 
        appointment.setStatus(Status.CANCELLED);
        appointmentRepository.save(appointment);
        }

        @Override
        public Boolean scheduleAppointment(AppointmentDTO appointmentDTO) throws HmsException {
            Boolean doctorExists = apiService.doctorExists(appointmentDTO.getDoctorId())
                .block();
                if(doctorExists == null || !doctorExists) {
                    throw new HmsException("DOCTOR_NOT_FOUND");
                }
            Boolean patientExists = apiService.patientExists(appointmentDTO.getPatientId())
                .block();
            if(patientExists == null || !patientExists) {
                throw new HmsException("PATIENT_NOT_FOUND");
        }
            appointmentDTO.setStatus(Status.SCHEDULED);
            return appointmentRepository.save(appointmentDTO.toEntity()).getId();
    }
    

    @Override
    public void completeAppointment(Long appointmentId) {
        // Implementation for completing an appointment
    }

    @Override
    public AppointmentDTO getAppointmentDetails(Long appointmentId) throws HmsException {
        return appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new HmsException("APPOINTMENT_NOT_FOUND")).toDTO();
    }

    @Override
    public void rescheduleAppointment(Long appointmentId, LocalTime newTime) {
        // Implementation for rescheduling an appointment
    }
    
    @Override
    public AppointmentDetails getAppointmentDetailsWithName(Long appointmentId) throws HmsException {
        main.java.com.hms.appointment.dto.AppointmentDTO appointmentDTO = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new HmsException("APPOINTMENT_NOT_FOUND"));
        
            DoctorDTO doctorDTO =profileClient.getDoctorById(appointmentDTO.getDoctorId());
            PatientDTO patientDTO = profileClient.getPatientById(appointmentDTO.getPatientId());
                return new AppointmentDetails(appointmentDTO.getId(),
                    appointmentDTO.getPatientId(),
                    appointmentDTO.getPatientId(),
                    patientDTO.getName(),
                    patientDTO.getEmail(),
                    patientDTO.getPhoneNumber(),

                    appointmentDTO.getDoctorId(),
                    appointmentDTO.getName(),
                    appointmentDTO.getAppointmentTime(),
                    appointmentDTO.getStatus(),
                    appointmentDTO.getReason(),
                    appointmentDTO.getNotes());

    }
    @Override
    public List<AppointmentDetails>getAllAppoinmentsByPatientId(Long patientId) throws HmsException {
        return appointmwntRepository.findAllByPatientId(patientId).stream()
        .map(appointment->{
            DoctorDTO doctorDTO=
            profileClient.getDoctorById(appointment.getDoctorId());
            appointment.setDoctorName(doctorDTO.getName());
            return appointment;
        }).toList();
    }
}
