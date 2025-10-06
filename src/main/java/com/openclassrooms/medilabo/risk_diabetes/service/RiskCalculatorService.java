package com.openclassrooms.medilabo.risk_diabetes.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.medilabo.risk_diabetes.beans.NoteBean;
import com.openclassrooms.medilabo.risk_diabetes.beans.PatientBean;
import com.openclassrooms.medilabo.risk_diabetes.enums.DiabetesRiskLevel;
import com.openclassrooms.medilabo.risk_diabetes.model.PatientRiskLevel;
import com.openclassrooms.medilabo.risk_diabetes.proxies.MicroserviceNotesProxy;
import com.openclassrooms.medilabo.risk_diabetes.proxies.MicroservicePatientsProxy;

@Service
public class RiskCalculatorService {

	@Autowired
	private MicroserviceNotesProxy notesProxy;
	
	@Autowired
	private MicroservicePatientsProxy patientsProxy;

	private static final List<String> TRIGGER_WORDS = List.of("hémoglobine a1c", "microalbumine", "taille", "poids",
			"fumeur", "fumeuse", "anormal", "cholestérol", "vertiges", "rechute", "réaction", "anticorps");
	
	public PatientRiskLevel calculatePatientRiskLevel(String patientId) {
		List<NoteBean> notes = notesProxy.getNotesByPatientId(patientId);
		PatientBean patient = patientsProxy.getPatientById(Integer.parseInt(patientId));
		LocalDate birthdate = patient.getBirthDate();
		int age = calculateAge(birthdate);
		String gender = patient.getGender().name();
		
		
		Integer counter = 0;
		for (NoteBean note : notes) {
			String content = note.getNote().toLowerCase();
			for (String triggerWord : TRIGGER_WORDS) {
				if (content.contains(triggerWord)) {
					counter++;
				}
			}
		}
		DiabetesRiskLevel risk = riskLevel(counter,age,gender);
		PatientRiskLevel patientRiskLevel = new PatientRiskLevel();
		patientRiskLevel.setPatientId(patientId);
		patientRiskLevel.setDiabetesRiskLevel(risk);
		patientRiskLevel.setTriggerWordsCounter(counter);
		return patientRiskLevel;
	}
	
	public DiabetesRiskLevel riskLevel(Integer counter, int age, String gender) {
		
		if (gender.contains("M") && age < 30 && counter >= 5) {
			return DiabetesRiskLevel.EARLYONSET;
		}
		
		else if (gender.contains("F") && age < 30 && counter >= 7) {
			return DiabetesRiskLevel.EARLYONSET;
		}
		
		else if(counter >= 8 && age >= 30) {
			return DiabetesRiskLevel.EARLYONSET;
		}
		
		else if (gender.contains("M") && age < 30 && counter >= 3) {
			return DiabetesRiskLevel.INDANGER;
		}
		
		else if (gender.contains("F") && age < 30 && counter >= 4) {
			return DiabetesRiskLevel.INDANGER;
		}
		
		else if(counter == 6 || counter == 7 && age >= 30) {
			return DiabetesRiskLevel.INDANGER;
		}
		
		else if (counter >= 2 && counter <= 5 && age >= 30) {
			return DiabetesRiskLevel.BORDERLINE;
		}
		
		else {
			return DiabetesRiskLevel.NONE;
		}
		
	}
	
	public static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
