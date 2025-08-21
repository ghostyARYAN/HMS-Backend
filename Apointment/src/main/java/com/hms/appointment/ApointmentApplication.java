package com.hms.appointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApointmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApointmentApplication.class, args);
	}

}
