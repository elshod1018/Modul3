package com.company.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Company{

	@JsonProperty("bs")
	private String bs;

	@JsonProperty("catchPhrase")
	private String catchPhrase;

	@JsonProperty("name")
	private String name;
}