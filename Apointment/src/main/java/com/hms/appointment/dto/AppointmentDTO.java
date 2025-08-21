package main.java.com.hms.appointment.dto;

import java.time.LocalTime;

import com.hms.appointment.dto.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
      private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalTime appointmentTime;
    private Status status;
    private String reason;
    private String notes;

    public AppointmentDTO toDTO(){
        return new AppointmentDTO(
            this.id,
            this.patientId,
            this.doctorId,
            this.appointmentTime,
            this.status,
            this.reason,
            this.notes
        );
    }
}

