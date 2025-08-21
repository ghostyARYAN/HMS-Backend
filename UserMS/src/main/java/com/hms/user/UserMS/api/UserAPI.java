package com.hms.user.UserMS.api;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hms.user.UserMS.dto.LoginDTO;
import com.hms.user.UserMS.dto.ResponseDTO;
import com.hms.user.UserMS.dto.UserDTO;
import com.hms.user.UserMS.exception.HmsException;
import com.hms.user.UserMS.jwt.JwtUtil;
import com.hms.user.UserMS.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
public class UserAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

 

    // ✅ Registration endpoint with validation
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDTO userDTO) throws HmsException {
        userService.registerUser(userDTO);
        return new ResponseEntity<>(new ResponseDTO("Account Created."), HttpStatus.CREATED);
    }

    // ✅ Login endpoint — method renamed for clarity
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) throws HmsException {
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
        }
        catch(AuthenticationException e){
            throw new HmsException("INVALID_CREDENTIALS");
        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(loginDTO.getEmail());
        final String jwt=jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(jwt,HttpStatus.OK);
        
    }
    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Test", HttpStatus.OK);
    }
}
