package main.java.com.hms.appointment.service;

import main.java.com.hms.appointment.dto.AppointmentDTO;
import main.java.com.hms.appointment.dto.AppointmentDetails;

import java.time.LocalTime;
import org.springframework.stereotype.Service;
import main.java.com.hms.appointment.exception.HmsException;
import main.java.com.hms.appointment.repository.AppointmentRepository;
import main.java.com.hms.appointment.dto.Status;
import main.java.com.hms.appointment.entity.Appointment;
import java.util.List;


public interface AppointmentService  {
    Long scheduleAppointment(AppointmentDTO appointmentDTO) throws HmsException;
    void cancelAppointment(Long appointmentId) throws HmsException;
    void completeAppointment(Long appointmentId);
    void getAppointmentDetails(Long appointmentId) throws HmsException; 
   void rescheduleAppointment(Long appointmentId, LocalTime newTime);
   AppointmentDetails getAppointmentDetailsWithName(Long appointmentId) throws HmsException;

   List<AppointmentDetails> getAllAppointmentsByPatientId(Long patientId);
}