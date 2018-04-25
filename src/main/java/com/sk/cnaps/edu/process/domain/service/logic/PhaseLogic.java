package com.sk.cnaps.edu.process.domain.service.logic;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.cnaps.domain.util.UtilsSupport;
import com.sk.cnaps.edu.process.domain.model.Phase;
import com.sk.cnaps.edu.process.domain.repository.PhaseRepository;
import com.sk.cnaps.edu.process.domain.service.PhaseService;

@Service
@Transactional
public class PhaseLogic implements PhaseService {
	@Autowired
	private PhaseRepository repository;
	
	@Override
	public void registerPhase(Phase phase) {
		if(phase.getId() != null) {
			throw new RuntimeException("Id Must Be Null Exception");
		}
		
		repository.save(phase);
	}

	@Override
	public void modifyPhase(Phase phase) {
		Phase found = repository.findOne(phase.getId());
		
		if(found == null) {
			throw new RuntimeException("Not Found Exception");
		}
		
		BeanUtils.copyProperties(phase, found, UtilsSupport.getNullPropertyNames(phase));
		repository.save(found);
	}

	@Override
	public List<Phase> findAllPhases() {
		return repository.findAll();
	}

	@Override
	public Phase findPhase(Long phaseId) {
		Phase found = repository.findOne(phaseId);
		
		if(found == null) {
			throw new RuntimeException("Not Found Exception");
		}
		
		return repository.findOne(phaseId);
	}

}
