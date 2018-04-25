package com.sk.cnaps.edu.process.application.sp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.cnaps.edu.process.domain.model.Phase;
import com.sk.cnaps.edu.process.domain.service.PhaseService;

@RestController
@RequestMapping("v1/cnaps/process")
public class PhaseRestController implements PhaseService {
	@Autowired
	private PhaseService service;
	
	@Override
	@PostMapping("phases")
	public void registerPhase(@RequestBody Phase phase) {
		service.registerPhase(phase);
	}
	
	@Override
	@PutMapping("phases")
	public void modifyPhase(@RequestBody Phase phase) {
		service.modifyPhase(phase);
	}

	@Override
	@GetMapping("phases")
	public List<Phase> findAllPhases() {
		return service.findAllPhases();
	}

	@Override
	@GetMapping("phases/{phaseId}")
	public Phase findPhase(@PathVariable Long phaseId) {
		return service.findPhase(phaseId);
	}

}
