package com.questionpro.grocery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	private Integer status;
	private String message;
	private boolean error;
	private Object data;
}
