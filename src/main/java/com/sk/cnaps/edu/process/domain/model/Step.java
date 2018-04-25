package com.sk.cnaps.edu.process.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sk.cnaps.domain.model.AbstractEntity;

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
public class Step extends AbstractEntity {
	private Integer orderNumber;
	
	private String title;
	
	@Column(columnDefinition="TEXT")
	private String content;
}