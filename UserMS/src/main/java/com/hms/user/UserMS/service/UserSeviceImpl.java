package com.hms.user.UserMS.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.user.UserMS.client.ProfileClient;
import com.hms.user.UserMS.dto.UserDTO;
import com.hms.user.UserMS.entity.User;
import com.hms.user.UserMS.exception.HmsException;
import com.hms.user.UserMS.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service("userService")
@Transactional
public class UserSeviceImpl implements UserService {

@Autowired
private UserRepository userRepository;

@Autowired PasswordEncoder passwordEncoder;
 

@Autowired
private ProfileClient profileClient;

   @Override
public void registerUser(UserDTO userDTO) throws HmsException {
    Optional<User> opt = UserRepository.findByEmail(userDTO.getEmail());
    if (opt.isPresent()) {
        throw new HmsException("USER_ALREADY_EXISTS");
    }

    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    Long profileId=null;
    if(userDTO.getRole().equals("DOCTOR")) {
       profileId = profileClient.addDoctor(userDTO);
    } else if(userDTO.getRole().equals("PATIENT")) {
        profileId = profileClient.addPatient(userDTO);
    }
    userDTO.setProfileId(profileId);
    User user = userDTO.toEntity();
    
}
    

    @Override
    public UserDTO loginUser(UserDTO userDTO) throws HmsException {
        User user = UserRepository.findByEmail(userDTO.getEmail()).orElseThrow(() -> new HmsException("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new HmsException("INVALID_CREDENTIALS");
        }
        user.setPassword(null);
        return user.toDTO();

    }          

    @Override
    public UserDTO getUserById(Long id) throws HmsException {
      return userRepository.findById(id).orElseThrow(() -> new HmsException("USER_NOT_FOUND")).toDTO();
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }


    @Override
    public UserDTO getUser(String email) throws HmsException {
        return UserRepository.findByEmail(email)
                .orElseThrow(() -> new HmsException("USER_NOT_FOUND"))
                .toDTO();
   
    
}
}