package com.openclassrooms.medilabo.risk_diabetes.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.medilabo.risk_diabetes.beans.PatientBean;

@FeignClient(name = "medilabo-patient-service")
public interface MicroservicePatientsProxy {

	@GetMapping(value = "/patient/{id}")
	PatientBean getPatientById(@PathVariable("id") Integer id);
}
