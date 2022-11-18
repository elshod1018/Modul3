package com.company.entity2;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geo{

	@SerializedName("lng")
	private String lng;

	@SerializedName("lat")
	private String lat;
}