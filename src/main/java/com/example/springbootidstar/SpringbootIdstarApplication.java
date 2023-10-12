package com.example.springbootidstar;

import com.example.springbootidstar.controller.uploaddata.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({FileStorageProperties.class})
public class SpringbootIdstarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootIdstarApplication.class, args);
	}

}
