package com.questionpro.grocery.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionpro.grocery.dto.GroceryBookRequestDTO;
import com.questionpro.grocery.dto.Response;
import com.questionpro.grocery.exception.ApiException;
import com.questionpro.grocery.exception.ResourceNotFoundException;
import com.questionpro.grocery.model.GroceryItem;
import com.questionpro.grocery.repository.GroceryItemRepository;
import com.questionpro.grocery.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	GroceryItemRepository groceryItemRepository;

	@Override
	public ResponseEntity<Response> viewAllGroceryItems() {
		log.info("UserServiceImpl viewAllGroceryItems method start ::{}");
		Response response = new Response();
		List<GroceryItem> itemList = new ArrayList<>();
		try {
			itemList = groceryItemRepository.findAll();
			log.info("groceryItem response ::{}", itemList);
			if (itemList == null || itemList.size() <= 0) {
				throw new ResourceNotFoundException(null);
			}

			response.setStatus(200);
			response.setMessage("fetched Data successfully!!");
			response.setError(false);
			response.setData(itemList);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception exception) {
			log.info("Exception while getting the data : " + exception);
			throw exception;

		}
	}

	@Override
	public ResponseEntity<Response> bookGroceryItems(GroceryBookRequestDTO itemIds) {

		log.info("UserServiceImpl manageInventory method with request ::{}  itemId : ", itemIds);
		Response response = new Response();
		Map<String, Integer> userShoppingCart = null;
		try {
			if (Objects.isNull(itemIds.getItems())) {
				throw new ApiException("Bad request!!");
			}

			for (Map.Entry<String, Integer> entry : itemIds.getItems().entrySet()) {
				userShoppingCart = addToCart(entry.getKey(), entry.getValue());
	        }
			log.info("Your Shopping Cart:");
			log.info("UserServiceImpl book the grocery data end{} ");

			response.setStatus(200);
			response.setMessage("Grocery Booked successfully!!");
			response.setError(false);
			response.setData(userShoppingCart);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception exception) {
			log.info("Exception while getting the data : " + exception);
			throw exception;

		}
	}

	private Map<String, Integer> addToCart(String item, Integer quantity) {
		log.info("addToCart  : item : " + item + " quantity : " + quantity);
		Map<String, Integer> availableGroceries = new HashMap<>();
		Map<String, Integer> userShoppingCart = new HashMap<>();
		
		List<GroceryItem> itemList = groceryItemRepository.findAll();
		log.info("available list : " + itemList);
		
		for(GroceryItem groceryItem : itemList) {
			availableGroceries.put(groceryItem.getName(), groceryItem.getInventory());
		}
		
		
		 if (availableGroceries.containsKey(item)) {
	            int availableQuantity = availableGroceries.get(item);
	            if (quantity <= availableQuantity) {
	                userShoppingCart.put(item, userShoppingCart.getOrDefault(item, 0) + quantity);
	                availableGroceries.put(item, availableQuantity - quantity);
	                log.info(quantity + " " + item + "(s) added to your cart.");
	                GroceryItem groceryItem = groceryItemRepository.findByName(item);
	                groceryItem.setInventory(availableQuantity - quantity);
	                groceryItemRepository.save(groceryItem);
	            } else {
	            	log.info("Sorry, only " + availableQuantity + " " + item + "(s) available.");
	            	throw new RuntimeException("Out of stock: " + item);
	            }
	        } else {
	        	log.info("Sorry, " + item + " is not available.");
	        }
		return userShoppingCart;
	}
	
}
