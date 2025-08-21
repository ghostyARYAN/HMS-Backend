package com.hms.user.UserMS.entity;

import com.hms.user.UserMS.dto.Roles;
import com.hms.user.UserMS.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Roles role;
    private Long profileId;
   

    public UserDTO toDTO(){
        return new UserDTO(this.id, this.name, this.email, this.password, this.role, this.profileId);
    }
}
