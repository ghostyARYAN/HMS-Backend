package com.hms.profile.entity;



import java.time.LocalDate;

import com.hms.profile.dto.BloodGroup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")

public class Patient {
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
    private String aadharNo;
    private BloodGroup bloodGroup;
    private String allergies;
    private String chronicDisease;
   
    public PatientDTO toEntity() {
       return new PatientDTO (
            this.id,
            this.name,
            this.email,
            this.dob,
            this.phone,
            this.address,
            this.aadharNo,
            this.bloodGroup,
            this.allergies,
            this.chronicDisease
       );
        
    }

 
}
