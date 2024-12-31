package com.example.commendeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CommendeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommendeServiceApplication.class, args);
	}

}
