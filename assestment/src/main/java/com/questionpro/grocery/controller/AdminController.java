package com.questionpro.grocery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionpro.grocery.dto.GroceryAddRequestDTO;
import com.questionpro.grocery.dto.Response;
import com.questionpro.grocery.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	Logger log = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	/**
	 * This method is add grocery item
	 *
	 * @return Response
	 * @author pradip kalwankar
	 * @since 02-01-2024
	 */
	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addGroceryItem(@RequestBody GroceryAddRequestDTO groceryItem) throws Exception {
		log.info("addGroceryItem method started in AdminController  : " + groceryItem);
		try {
			ResponseEntity<Response> response = adminService.addGroceryItem(groceryItem);
			log.info("addGroceryItem method end in AdminController  : " + response);
			return response;
		} catch (Exception ex) {
			log.info("addGroceryItem method end in AdminController : ");
			throw ex;
		}
	}

	/**
	 * This method is delete grocery item
	 *
	 * @return Response
	 * @author pradip kalwankar
	 * @since 02-01-2024
	 */
	@DeleteMapping("/remove/{itemId}")
	public ResponseEntity<Response> removeGroceryItem(@PathVariable Long itemId) {
		log.info("removeGroceryItem method started in AdminController  : " + itemId);
		try {
			ResponseEntity<Response> response = adminService.removeGroceryItem(itemId);
			log.info("removeGroceryItem method end in AdminController  : " + response);
			return response;
		} catch (Exception ex) {
			log.info("addGroceryItem method end in AdminController : ");
			throw ex;
		}
	}

	/**
	 * This method is update grocery item
	 *
	 * @return Response
	 * @author pradip kalwankar
	 * @since 02-01-2024
	 */
	@PutMapping("/update")
	public ResponseEntity<Response> updateGroceryItem(@RequestBody GroceryAddRequestDTO groceryAddRequestDTO) {

		log.info("updateGroceryItem method started in AdminController  : " + groceryAddRequestDTO);
		try {
			ResponseEntity<Response> response = adminService.updateGroceryItem(groceryAddRequestDTO);
			log.info("removeGroceryItem method end in AdminController  : " + response);
			return response;
		} catch (Exception ex) {
			log.info("updateGroceryItem method end in AdminController : ");
			throw ex;
		}
	}
	
	/**
	 * This method is manage grocery item inventory
	 *
	 * @return Response
	 * @author pradip kalwankar
	 * @since 02-01-2024
	 */
	@PutMapping("/items/{id}/manageInventory/{newQuantity}")
	public ResponseEntity<Response> manageInventory(@PathVariable Long id, @PathVariable Integer newQuantity) {
		
		log.info("manageGroceryItem method started in AdminController id {} : " + id + "New quantity {} : " + newQuantity);
		try {
			ResponseEntity<Response> response = adminService.manageInventory(id,newQuantity);
			log.info("manageGroceryItem method end in AdminController  : " + response);
			return response;
		} catch (Exception ex) {
			log.info("manageGroceryItem method end in AdminController : ");
			throw ex;
		}
	}
	
	/**
	 * This method is get all grocery item
	 *
	 * @return Response
	 * @author pradip kalwankar
	 * @since 02-01-2024
	 */
	@GetMapping("/items")
	public ResponseEntity<Response> viewAllGroceryItems() {
		
		log.info("viewAllGroceryItems method started in AdminController {} : " );
		try {
			ResponseEntity<Response> response = adminService.viewAllGroceryItems();
			log.info("viewAllGroceryItems method end in AdminController  : " + response);
			return response;
		} catch (Exception ex) {
			log.info("viewAllGroceryItems method end in AdminController : ");
			throw ex;
		}
	}
}
