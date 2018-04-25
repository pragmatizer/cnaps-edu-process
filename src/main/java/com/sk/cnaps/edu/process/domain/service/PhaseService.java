package com.sk.cnaps.edu.process.domain.service;

import java.util.List;

import com.sk.cnaps.edu.process.domain.model.Phase;

public interface PhaseService {
	void registerPhase(Phase phase);
	void modifyPhase(Phase phase);
	List<Phase> findAllPhases();
	Phase findPhase(Long phaseId);
}
