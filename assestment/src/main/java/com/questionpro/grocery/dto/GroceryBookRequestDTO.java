package com.questionpro.grocery.dto;

import java.util.Map;

import lombok.Data;

@Data
public class GroceryBookRequestDTO {

	private Map<String, Integer> items;
}
