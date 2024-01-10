package com.questionpro.grocery.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionpro.grocery.dto.GroceryBookRequestDTO;
import com.questionpro.grocery.dto.Response;
import com.questionpro.grocery.model.GroceryItem;
import com.questionpro.grocery.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/**
	 * This method is get all grocery item
	 *
	 * @return Response
	 * @author pradip kalwankar
	 * @since 02-01-2024
	 */
	@GetMapping("/view-items")
	public ResponseEntity<Response> viewAllGroceryItems() {
		log.info("viewAllGroceryItems method started in UserController {} : " );
		try {
			ResponseEntity<Response> response = userService.viewAllGroceryItems();
			log.info("viewAllGroceryItems method end in UserController  : " + response);
			return response;
		} catch (Exception ex) {
			log.info("viewAllGroceryItems method end in UserController : ");
			throw ex;
		}
	}

	/**
	 * This method is book grocery item
	 *
	 * @return Response
	 * @author pradip kalwankar
	 * @since 02-01-2024
	 */
	@PostMapping("/book")
	public ResponseEntity<Response> bookGroceryItems(@RequestBody GroceryBookRequestDTO items) {
		log.info("bookGroceryItems method started in UserController {} : " );
		try {
			ResponseEntity<Response> response = userService.bookGroceryItems(items);
			log.info("bookGroceryItems method end in UserController  : " + response);
			return response;
		} catch (Exception ex) {
			log.info("bookGroceryItems method end in UserController : ");
			throw ex;
		}
	}
}
