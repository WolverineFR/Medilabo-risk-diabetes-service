package com.openclassrooms.medilabo.risk_diabetes.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.medilabo.risk_diabetes.beans.NoteBean;

/**
 * Proxy Feign pour interagir avec le microservice de gestion des notes
 * médicales.
 * 
 * Cette interface permet au service medilabo-risk-diabetes-service d’obtenir
 * les notes d’un patient stockées dans le microservice medilabo-note-service.
 *
 * Elle est utilisée pour analyser les antécédents médicaux et calculer le
 * risque de diabète.
 */
@FeignClient(name = "medilabo-note-service")
public interface MicroserviceNotesProxy {

	/**
	 * Récupère toutes les notes associées à un patient à partir de son identifiant.
	 *
	 * @param patientId identifiant du patient dont on souhaite récupérer les notes
	 * @return une liste de NoteBean contenant les notes du patient
	 */
	@GetMapping(value = "/notes/patient/{patientId}")
	List<NoteBean> getNotesByPatientId(@PathVariable("patientId") String patientId);

}
