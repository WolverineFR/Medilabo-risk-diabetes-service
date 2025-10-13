package com.openclassrooms.medilabo.risk_diabetes.model;

import com.openclassrooms.medilabo.risk_diabetes.enums.DiabetesRiskLevel;

public class PatientRiskLevel {

	private String patientId;
	private DiabetesRiskLevel diabetesRiskLevel;
	private Integer triggerWordsCounter;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public DiabetesRiskLevel getDiabetesRiskLevel() {
		return diabetesRiskLevel;
	}

	public void setDiabetesRiskLevel(DiabetesRiskLevel diabetesRiskLevel) {
		this.diabetesRiskLevel = diabetesRiskLevel;
	}

	public Integer getTriggerWordsCounter() {
		return triggerWordsCounter;
	}

	public void setTriggerWordsCounter(Integer triggerWordsCounter) {
		this.triggerWordsCounter = triggerWordsCounter;
	}

}
