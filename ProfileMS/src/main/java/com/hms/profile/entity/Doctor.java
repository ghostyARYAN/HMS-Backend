package main.java.com.hms.profile.entity;

import lombok.Data;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import main.java.com.hms.profile.dto.BloodGroup;

@Data
@Entity
@NoArgsConstructor
public class Doctor {
	@Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private String phone;
    private String address;
    @Column(unique = true)
    private String licenseNo;
    private String specialization;
    private String department;
    private Integer totalExp;


    public DoctorDTO toDTO() {
        return new DoctorDTO(
            this.id,
            this.name,
            this.email,
            this.dob,
            this.phone,
            this.address,
            this.licenseNo,
            this.specialization,
            this.department,
            this.totalExp
        );
    }

}
