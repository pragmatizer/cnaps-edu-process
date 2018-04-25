package com.sk.cnaps.edu.process.domain.model;

import java.util.List;

import com.sk.cnaps.domain.model.ValueObject;

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
public class Resource extends ValueObject {
	private String title;
	private String url;
	private ResourceType resourceType;
}
