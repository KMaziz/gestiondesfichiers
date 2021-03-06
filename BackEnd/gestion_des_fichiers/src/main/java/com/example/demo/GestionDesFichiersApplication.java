package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.controller.FileController;
import com.example.demo.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class GestionDesFichiersApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDesFichiersApplication.class, args);
	}

}
