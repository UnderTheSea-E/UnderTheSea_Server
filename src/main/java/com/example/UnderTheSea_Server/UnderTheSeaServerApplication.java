package com.example.UnderTheSea_Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UnderTheSeaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnderTheSeaServerApplication.class, args);
	}

}
