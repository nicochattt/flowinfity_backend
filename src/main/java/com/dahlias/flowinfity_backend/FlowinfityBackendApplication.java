package com.dahlias.flowinfity_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FlowinfityBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowinfityBackendApplication.class, args);
	}

}
