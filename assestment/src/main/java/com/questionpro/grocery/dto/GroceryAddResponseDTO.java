package com.questionpro.grocery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class GroceryAddResponseDTO {

	private Long id;

	private String name;

	private Double price;

	private Integer inventory;
}
