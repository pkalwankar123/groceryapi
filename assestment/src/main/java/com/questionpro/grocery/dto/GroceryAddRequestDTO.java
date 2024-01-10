package com.questionpro.grocery.dto;

import lombok.Data;

@Data
public class GroceryAddRequestDTO {

	private Long id;
	private String name;
	private Double price;
	private Integer inventory;
}
