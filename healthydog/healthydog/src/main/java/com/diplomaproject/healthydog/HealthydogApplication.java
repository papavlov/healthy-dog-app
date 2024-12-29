package com.diplomaproject.healthydog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication/*(exclude = { SecurityAutoConfiguration.class })*/
@EnableScheduling
public class HealthydogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthydogApplication.class, args);



	}

}
