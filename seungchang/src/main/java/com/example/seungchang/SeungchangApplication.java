package com.example.seungchang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SeungchangApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeungchangApplication.class, args);
	}

}
