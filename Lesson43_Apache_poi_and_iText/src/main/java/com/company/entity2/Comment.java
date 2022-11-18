package com.company.entity2;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment{

	@SerializedName("name")
	private String name;

	@SerializedName("postId")
	private Integer postId;

	@SerializedName("id")
	private Integer id;

	@SerializedName("body")
	private String body;

	@SerializedName("email")
	private String email;
}