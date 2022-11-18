package com.company.entity;

import lombok.Data;

@Data
public class Todo{
	private Integer id;
	private Boolean completed;
	private String title;
	private Integer userId;
}
