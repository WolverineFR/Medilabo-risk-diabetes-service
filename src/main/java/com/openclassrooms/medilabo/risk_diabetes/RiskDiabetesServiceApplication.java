package com.openclassrooms.medilabo.risk_diabetes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RiskDiabetesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskDiabetesServiceApplication.class, args);
	}

}
