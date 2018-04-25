package com.sk.cnaps.edu.process.domain.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sk.cnaps.domain.model.AbstractEntity;
import com.sk.cnaps.domain.model.AggregateProxy;
import com.sk.cnaps.domain.model.AggregateRelationType;
import com.sk.cnaps.domain.model.AggregateRoot;

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
@Table(uniqueConstraints={@UniqueConstraint(columnNames= { "number", "title"})})
public class Phase extends AbstractEntity implements AggregateRoot {	
	@Column(nullable=false)
	private String number;
	
	@Column(nullable=false)
	private String title;
	
	private String description;
	
	private String consideration;
		
	@Convert(converter=AggregateProxy.class)
	@Column(columnDefinition="TEXT")
	@Builder.Default
	private AggregateProxy<Activity> activitiesAggregate = new AggregateProxy<>(AggregateRelationType.ONE_TO_MANY);
	
	//@Builder.Default
	//@ElementCollection(fetch=FetchType.EAGER)
	//private List<Long> activityIds = new ArrayList<Long>();
}
