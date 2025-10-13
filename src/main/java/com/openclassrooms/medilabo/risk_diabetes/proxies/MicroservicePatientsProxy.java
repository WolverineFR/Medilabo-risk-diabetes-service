package com.openclassrooms.medilabo.risk_diabetes.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.medilabo.risk_diabetes.beans.PatientBean;

/**
 * Proxy Feign pour interagir avec le microservice de gestion des patients.
 * 
 * Cette interface permet au service medilabo-risk-diabetes-service d’accéder
 * aux informations des patients stockées dans le microservice
 * medilabo-patient-service.
 */
@FeignClient(name = "medilabo-patient-service")
public interface MicroservicePatientsProxy {

	/**
	 * Récupère les informations d’un patient à partir de son identifiant unique.
	 *
	 * @param id identifiant du patient
	 * @return un PatientBean contenant les informations du patient
	 */
	@GetMapping(value = "/patient/{id}")
	PatientBean getPatientById(@PathVariable("id") Integer id);
}
