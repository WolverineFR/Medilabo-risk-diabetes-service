package com.openclassrooms.medilabo.risk_diabetes.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.medilabo.risk_diabetes.model.PatientRiskLevel;
import com.openclassrooms.medilabo.risk_diabetes.service.RiskCalculatorService;

/**
 * Contrôleur REST pour le calcul du risque de diabète d’un patient.
 * 
 * Expose un endpoint pour récupérer le niveau de risque d’un patient à partir
 * de son identifiant en interrogeant RiskCalculatorService}.
 */
@RestController
public class RiskCalculatorController {

	private RiskCalculatorService calculatorService;
	private static final Logger logger = LogManager.getLogger(RiskCalculatorController.class);

	public RiskCalculatorController(RiskCalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	/**
	 * Récupère le niveau de risque de diabète pour un patient donné.
	 *
	 * @param patientId identifiant du patient
	 * @return ResponseEntity contenant PatientRiskLevel et status HTTP 200
	 */
	@GetMapping("/calculateRisk/{patientId}")
	public ResponseEntity<PatientRiskLevel> getPatientRiskLevel(@PathVariable String patientId) {
		PatientRiskLevel patientRiskLevel = calculatorService.calculatePatientRiskLevel(patientId);
		logger.info("Niveau de risque du patient : ", patientRiskLevel.getDiabetesRiskLevel());
		return ResponseEntity.ok(patientRiskLevel);
	}
}
