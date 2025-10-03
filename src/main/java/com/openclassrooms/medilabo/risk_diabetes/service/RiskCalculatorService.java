package com.openclassrooms.medilabo.risk_diabetes.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.openclassrooms.medilabo.risk_diabetes.beans.NoteBean;
import com.openclassrooms.medilabo.risk_diabetes.beans.PatientBean;
import com.openclassrooms.medilabo.risk_diabetes.enums.DiabetesRiskLevel;
import com.openclassrooms.medilabo.risk_diabetes.model.PatientRiskLevel;
import com.openclassrooms.medilabo.risk_diabetes.proxies.MicroserviceNotesProxy;
import com.openclassrooms.medilabo.risk_diabetes.proxies.MicroservicePatientsProxy;

public class RiskCalculatorService {

	private MicroserviceNotesProxy notesProxy;
	private MicroservicePatientsProxy patientsProxy;

	private static final List<String> TRIGGER_WORDS = List.of("hémoglobine a1c", "microalbumine", "taille", "poids",
			"fumeur", "fumeuse", "anormal", "cholestérol", "vertiges", "rechute", "réaction", "anticorps");
	
	public PatientRiskLevel calculatePatientRiskLevel(String patientId) {
		List<NoteBean> notes = notesProxy.getNotesByPatientId(patientId);
		PatientBean patient = patientsProxy.getPatientById(Integer.parseInt(patientId));
		LocalDate birthdate = patient.getBirthDate();
		int age = calculateAge(birthdate);
		
		
		Integer counter = 0;
		for (NoteBean note : notes) {
			String content = note.getNote().toLowerCase();
			for (String triggerWord : TRIGGER_WORDS) {
				if (content.contains(triggerWord)) {
					counter++;
				}
			}
		}
		DiabetesRiskLevel risk = riskLevel(counter,age);
		PatientRiskLevel patientRiskLevel = new PatientRiskLevel();
		patientRiskLevel.setPatientId(patientId);
		patientRiskLevel.setDiabetesRiskLevel(risk);
		patientRiskLevel.setTriggerWordsCounter(counter);
		return patientRiskLevel;
	}
	
	private DiabetesRiskLevel riskLevel(Integer counter, int age) {
		return null;
	}
	
	public static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
