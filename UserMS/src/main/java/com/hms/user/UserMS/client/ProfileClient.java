package com.hms.user.UserMS.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


import com.hms.user.UserMS.config.FeignClientInterceptor;
import com.hms.user.UserMS.dto.UserDTO;

@FeignClient(name = "ProfileMS", configuration = FeignClientInterceptor.class)
public interface ProfileClient {
    @PostMapping("/profile/doctor/add")
   Long addDoctor(@RequestBody UserDTO userDTO);
     @PostMapping("/profile/patient/add")
   Long addPatient(@RequestBody UserDTO userDTO);
}
