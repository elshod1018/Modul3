package com.company.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Address{

	@JsonProperty("zipcode")
	private String zipcode;

	@JsonProperty("geo")
	private Geo geo;

	@JsonProperty("suite")
	private String suite;

	@JsonProperty("city")
	private String city;

	@JsonProperty("street")
	private String street;
}