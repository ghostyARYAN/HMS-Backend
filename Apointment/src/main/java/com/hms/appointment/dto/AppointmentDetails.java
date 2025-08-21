package main.java.com.hms.appointment.dto;

import java.time.LocalTime;
import com.hms.appointment.dto.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDetails {
       private Long id;
    private Long patientId;
    private String patientName;
    private String patientEmail;
    private String patientPhone;
    private Long doctorId;
    private LocalTime appointmentTime;
    private Status status;
    private String reason;
    private String notes;
}
