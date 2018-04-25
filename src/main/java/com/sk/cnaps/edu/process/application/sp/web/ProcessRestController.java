package com.sk.cnaps.edu.process.application.sp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.cnaps.edu.process.domain.model.Activity;
import com.sk.cnaps.edu.process.domain.model.Phase;
import com.sk.cnaps.edu.process.domain.service.ProcessService;

@RestController
@RequestMapping("v1/cnaps/process")
public class ProcessRestController /* implements ProcessService */ {
	@Autowired
	private ProcessService service;
	
	@GetMapping("phases+activities/{phaseId}")
	public ResponseEntity<Phase> findPhaseWithActivitiesById(@PathVariable Long phaseId) {
		try {
			return new ResponseEntity<>(service.findPhaseWithActivitiesById(phaseId), HttpStatus.OK);
		} catch(NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("activities+tasks/{activityId}")
	public ResponseEntity<Activity> findActivityWithTasksById(Long activityId) {
		try {
			return new ResponseEntity<>(service.findActivityWithTasksById(activityId), HttpStatus.OK);
		} catch(NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
