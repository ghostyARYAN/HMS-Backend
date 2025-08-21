package com.hms.user.UserMS.dto;

import com.hms.user.UserMS.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
        message = "Password must be at least 8 characters long and max 15 characters and contain at least one letter, one number, and one special character"
    )
    private String password;

    private Roles role;
    private Long profileId;
   

    public User toEntity() {
        return new User(this.id, this.name, this.email, this.password, this.role, this.profileId);
    }
}