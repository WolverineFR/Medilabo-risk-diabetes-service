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

@RestController
public class RiskCalculatorController {

	@Autowired
	private RiskCalculatorService calculatorService;
	
	private static final Logger logger = LogManager.getLogger(RiskCalculatorController.class);
	
	@Autowired
	public RiskCalculatorController(RiskCalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}
	
	@GetMapping("/calculateRisk/{patientId}")
	public ResponseEntity<PatientRiskLevel> getPatientRiskLevel(@PathVariable String patientId) {
		PatientRiskLevel patientRiskLevel = calculatorService.calculatePatientRiskLevel(patientId);
		logger.info("Niveau de risque du patient : ", patientRiskLevel.getDiabetesRiskLevel());
		return ResponseEntity.ok(patientRiskLevel);
	}
}
