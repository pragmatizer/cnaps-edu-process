package com.sk.cnaps.edu.process.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.sk.cnaps.domain.model.AbstractEntity;
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
public class Task extends AbstractEntity implements AggregateRoot {
	@Column(nullable=false)
	private String number;
	
	@Column(nullable=false)
	private String title;
	
	private String description;
	
    @Enumerated(value=EnumType.STRING)
	private RoleType roleType;
    
	@Convert(converter=Resource.class)
	@Column(columnDefinition="TEXT")
    private Resource input;
	
	@Convert(converter=Resource.class)
	@Column(columnDefinition="TEXT")
    private Resource output;
    
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Builder.Default
	private List<Step> steps = new ArrayList<Step>();
}
