package com.sk.cnaps.edu.process.domain.service.logic;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.cnaps.edu.process.domain.model.Activity;
import com.sk.cnaps.edu.process.domain.model.Phase;
import com.sk.cnaps.edu.process.domain.repository.ActivityRepository;
import com.sk.cnaps.edu.process.domain.repository.PhaseRepository;
import com.sk.cnaps.edu.process.domain.repository.TaskRepository;
import com.sk.cnaps.edu.process.domain.service.ProcessService;


@Service
@Transactional
public class ProcessLogic implements ProcessService {
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private PhaseRepository phaseRepository;

	@Override
	public Phase findPhaseWithActivitiesById(Long phaseId) {
		Phase phase = phaseRepository.findOne(phaseId);
		if(phaseId == null) {
			throw new RuntimeException("Id Must Be Null Exception");
		}
		
		phase.getActivitiesAggregate().fillValues(activityRepository);
		return phase;
	}

	@Override
	public Activity findActivityWithTasksById(Long activityId) {
		Activity activity = activityRepository.findOne(activityId);
		if(activityId == null) {
			throw new RuntimeException("Id Must Be Null Exception");
		}
		
		activity.getTasksAggregate().fillValues(taskRepository);
		return activity;
	}

}
