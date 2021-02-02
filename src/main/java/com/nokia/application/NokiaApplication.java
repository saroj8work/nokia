package com.nokia.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.nokia.application")
@SpringBootApplication
public class NokiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NokiaApplication.class, args);
	}

}
