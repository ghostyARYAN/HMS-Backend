package com.hms.user.UserMS.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hms.user.UserMS.dto.UserDTO;
import com.hms.user.UserMS.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    try{
        UserDTO dto=userService.getUser(email);
        return new CustomerUserDetails(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRole(),
                dto.getName(),
                dto.getProfileId(),
                null // Assuming authorities are not needed here
        );
    }
    catch (Exception e) {
        e.printStackTrace();   
    }
return null;
    }
    
 
    
}
