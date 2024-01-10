package com.questionpro.grocery.service;

import org.springframework.http.ResponseEntity;

import com.questionpro.grocery.dto.GroceryAddRequestDTO;
import com.questionpro.grocery.dto.Response;

public interface AdminService {

	ResponseEntity<Response> addGroceryItem(GroceryAddRequestDTO groceryItem) throws Exception;

	ResponseEntity<Response> viewAllGroceryItems();

	ResponseEntity<Response> removeGroceryItem(Long itemId);

	ResponseEntity<Response> updateGroceryItem(GroceryAddRequestDTO groceryAddRequestDTO);

	ResponseEntity<Response> manageInventory(Long id, Integer newQuantity);

}
