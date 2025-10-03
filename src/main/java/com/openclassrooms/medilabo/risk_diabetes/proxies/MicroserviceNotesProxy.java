package com.openclassrooms.medilabo.risk_diabetes.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.medilabo.risk_diabetes.beans.NoteBean;


@FeignClient(name = "medilabo-note-service")
public interface MicroserviceNotesProxy {

	@GetMapping(value = "/notes/patient/{patientId}")
	List<NoteBean> getNotesByPatientId(@PathVariable("patientId") String patientId);
	
}
