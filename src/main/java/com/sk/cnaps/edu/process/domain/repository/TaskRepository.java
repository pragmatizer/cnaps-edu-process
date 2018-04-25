package com.sk.cnaps.edu.process.domain.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sk.cnaps.domain.repository.AggregateRepository;
import com.sk.cnaps.edu.process.domain.model.Activity;
import com.sk.cnaps.edu.process.domain.model.Task;

//@RepositoryRestResource(collectionResourceRel="tasks", path="tasks")
public interface TaskRepository extends PagingAndSortingRepository<Task, Long>, AggregateRepository<Task> {
	//@Query("SELECT DISTINCT t FROM Task t JOIN FETCH t.steps")
	public List<Task> findAll();
	//public List<Task> findByIdIn(List<Long> ids);
}
