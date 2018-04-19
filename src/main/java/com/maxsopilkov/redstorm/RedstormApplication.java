package com.maxsopilkov.redstorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.maxsopilkov"} , exclude = JpaRepositoriesAutoConfiguration.class)
public class RedstormApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedstormApplication.class, args);
	}
}
