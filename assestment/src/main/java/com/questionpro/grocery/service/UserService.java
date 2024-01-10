package com.questionpro.grocery.service;

import org.springframework.http.ResponseEntity;

import com.questionpro.grocery.dto.GroceryBookRequestDTO;
import com.questionpro.grocery.dto.Response;

public interface UserService {

	ResponseEntity<Response> viewAllGroceryItems();

	ResponseEntity<Response> bookGroceryItems(GroceryBookRequestDTO itemIds);
}
