package com.sk.cnaps.edu.process.domain.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sk.cnaps.domain.repository.AggregateRepository;
import com.sk.cnaps.edu.process.domain.model.Activity;

//@RepositoryRestResource(collectionResourceRel="activities", path="activities")
public interface ActivityRepository extends PagingAndSortingRepository<Activity, Long>, AggregateRepository<Activity> {
	//@Query("SELECT DISTINCT t FROM Task t JOIN FETCH t.steps")
	public List<Activity> findAll();
	//public List<Activity> findByIdIn(List<Long> ids);
}
