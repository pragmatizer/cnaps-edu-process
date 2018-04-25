package com.sk.cnaps.edu.process.domain.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sk.cnaps.edu.process.domain.model.Phase;

//@RepositoryRestResource(collectionResourceRel="phases", path="phases")
public interface PhaseRepository extends PagingAndSortingRepository<Phase, Long> {
	//@Query("SELECT DISTINCT t FROM Task t JOIN FETCH t.steps")
	public List<Phase> findAll();
}
