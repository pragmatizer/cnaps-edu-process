package com.sk.cnaps.edu.process.domain.service;

import com.sk.cnaps.edu.process.domain.model.Activity;
import com.sk.cnaps.edu.process.domain.model.Phase;

public interface ProcessService {
	Phase findPhaseWithActivitiesById(Long phaseId);
	Activity findActivityWithTasksById(Long taskId);
}
