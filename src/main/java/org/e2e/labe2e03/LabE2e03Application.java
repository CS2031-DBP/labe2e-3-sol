package org.e2e.labe2e03;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LabE2e03Application {

	public static void main(String[] args) {
		SpringApplication.run(LabE2e03Application.class, args);
	}

}
