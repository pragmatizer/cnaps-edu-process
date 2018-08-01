package com.sk.cnaps.edu.process.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import com.sk.cnaps.domain.model.AbstractEntity;
import com.sk.cnaps.domain.model.AggregateProxy;
import com.sk.cnaps.domain.model.AggregateRoot;
import com.sk.cnaps.domain.util.ListConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Activity extends AbstractEntity implements AggregateRoot {	
	@Column(nullable=false)
	private String number;
	
	@Column(nullable=false)
	private String title;
	
	private String description;
	
	@Convert(converter=ListConverter.class)
	@Column(columnDefinition="TEXT")
	@Builder.Default
	private List<Resource> guideLines = new ArrayList<>();
	
	@Convert(converter=ListConverter.class)
	@Column(columnDefinition="TEXT")
	@Builder.Default
	private List<Resource> manuals = new ArrayList<>();
	
	@Convert(converter=ListConverter.class)
	@Column(columnDefinition="TEXT")
	@Builder.Default
	private List<Resource> templateOrSamples = new ArrayList<>();
	
	@Convert(converter=AggregateProxy.class)
	@Column(columnDefinition="TEXT")
	@Builder.Default
	private AggregateProxy<Task> tasksAggregate = new AggregateProxy<>();
	
	//@ElementCollection
	//@Builder.Default
	//private List<Long> taskIds = new ArrayList<Long>();
}
