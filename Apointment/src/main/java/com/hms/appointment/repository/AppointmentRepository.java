package main.java.com.hms.appointment.repository;

import com.hms.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.hms.appointment.dto.AppointmentDetails;

import org.springframework.data.repository.CrudRepository;


public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
  
    @Query("Select new com.hms.appointment.dto.AppointmentDetails(a.id, a.patientId, null, null, a.doctorId, null, a.appointmentTime, a.status, a.reason, a.notes)FROM Appointment a WHERE a.patientId= ?1 patientId")
    
    List<AppointmentDetails> findAllByPatientId(Long patientId);
}
