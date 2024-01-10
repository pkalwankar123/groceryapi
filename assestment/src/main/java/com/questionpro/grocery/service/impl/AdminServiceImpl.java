package com.questionpro.grocery.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionpro.grocery.dto.GroceryAddRequestDTO;
import com.questionpro.grocery.dto.GroceryAddResponseDTO;
import com.questionpro.grocery.dto.Response;
import com.questionpro.grocery.exception.ApiException;
import com.questionpro.grocery.exception.ResourceNotFoundException;
import com.questionpro.grocery.model.GroceryItem;
import com.questionpro.grocery.repository.GroceryItemRepository;
import com.questionpro.grocery.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	GroceryItemRepository groceryItemRepository;

	@Override
	public ResponseEntity<Response> viewAllGroceryItems() {		
		log.info("AdminServiceImpl viewAllGroceryItems method start ::{}");
		Response response = new Response();
		List<GroceryItem> itemList = new ArrayList<>();
		try {
			
			itemList = groceryItemRepository.findAll();
			log.info("groceryItem response ::{}", itemList);
			if(itemList == null || itemList.size() <= 0) {
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
	public ResponseEntity<Response> removeGroceryItem(Long itemId) {
		log.info("AdminServiceImpl removeGroceryItem method with request ::{}", itemId);
		Response response = new Response();
		Optional<GroceryItem> groceryItemOptional = Optional.empty();
		GroceryItem groceryItem = new GroceryItem();
		try {
			if (itemId == null) {
				throw new ApiException("Bad request!!");
			}
			groceryItemOptional = groceryItemRepository.findById(itemId);
			groceryItem = groceryItemOptional.get();
			log.info("groceryItem response ::{}", groceryItem);
			if(groceryItem == null) {
				throw new ResourceNotFoundException(itemId);
			}
			
		groceryItemRepository.deleteById(itemId);
		
		response.setStatus(200);
		response.setMessage("Grocery Data removed successfully!!");
		response.setError(false);
		response.setData("Grocery Data removed successfully!!");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception exception) {
			log.info("Exception while getting the data : " + exception);
			throw exception;

		}
	}

	
	@Override
	public ResponseEntity<Response> manageInventory(Long itemId, Integer quantity) {
		log.info("AdminServiceImpl manageInventory method with request ::{}  itemId : ", itemId + " Quantity : " + quantity);
		Response response = new Response();
		GroceryItem groceryItem = new GroceryItem();
		Optional<GroceryItem> groceryItemOptional = Optional.empty();
		try {
				if (itemId == null || quantity == null) {
					throw new ApiException("Bad request!!");
				}
				
				groceryItemOptional = groceryItemRepository.findById(itemId);
				groceryItem = groceryItemOptional.get();
				log.info("groceryItem response ::{}", groceryItem);
				if(groceryItem == null) {
					throw new ResourceNotFoundException(itemId);
				}
				
				log.info("AdminServiceImpl manageInventory the grocery data start{}");
				groceryItem.setInventory(quantity);
				
				groceryItemRepository.save(groceryItem);
				log.info("AdminServiceImpl manageInventory the grocery data end{} ", groceryItem);

				response.setStatus(200);
				response.setMessage("Grocery Data Updated successfully!!");
				response.setError(false);
				response.setData("Grocery Data Updated successfully!!");
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			} catch (Exception exception) {
				log.info("Exception while getting the data : " + exception);
				throw exception;

			}
		}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Response> addGroceryItem(GroceryAddRequestDTO groceryAddRequestDTO) throws Exception {
		log.info("AdminServiceImpl addGroceryItem method with request ::{}", groceryAddRequestDTO);
		Response response = new Response();
		GroceryItem groceryItem = new GroceryItem();
		try {
			if (groceryAddRequestDTO == null || groceryAddRequestDTO.getInventory() == null
					|| groceryAddRequestDTO.getName() == null || groceryAddRequestDTO.getPrice() == null) {
				throw new ApiException("Bad request!!");
			}
			log.info("AdminServiceImpl saving the grocery data start{}");
			groceryItem.setInventory(groceryAddRequestDTO.getInventory());
			groceryItem.setName(groceryAddRequestDTO.getName());
			groceryItem.setPrice(groceryAddRequestDTO.getPrice());
			groceryItemRepository.save(groceryItem);
			log.info("AdminServiceImpl saving the grocery data end{}" , groceryItem);

			log.info("AdminServiceImpl converting db data model to response dto start{}");

			response.setStatus(200);
			response.setMessage("Grocery Data Saved successfully!!");
			response.setError(false);
			response.setData(groceryItem);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception exception) {
			log.info("Exception while getting the data : " + exception);
			throw exception;

		}

	}

	@Override
	public ResponseEntity<Response> updateGroceryItem(GroceryAddRequestDTO groceryAddRequestDTO) {
		log.info("AdminServiceImpl updateGroceryItem method with request ::{}", groceryAddRequestDTO);
		Response response = new Response();
		GroceryItem groceryItem = new GroceryItem();
		Optional<GroceryItem> groceryItemOptional = Optional.empty();
		GroceryAddResponseDTO groceryAddResponseDTO = new GroceryAddResponseDTO();
		try {
			if (groceryAddRequestDTO == null || groceryAddRequestDTO.getId() == null) {
				throw new ApiException("Bad request!!");
			}
			
			groceryItemOptional = groceryItemRepository.findById(groceryAddRequestDTO.getId());
			groceryItem = groceryItemOptional.get();
			log.info("groceryItem response ::{}", groceryItem);
			if(groceryItem == null) {
				throw new ResourceNotFoundException(groceryAddRequestDTO.getId());
			}
			
			log.info("AdminServiceImpl updating the grocery data start{}");
			groceryItem.setId(groceryAddRequestDTO.getId() != null ? groceryAddRequestDTO.getId() : groceryItem.getId());
			groceryItem.setInventory(groceryAddRequestDTO.getInventory() != null ? groceryAddRequestDTO.getInventory() : groceryItem.getInventory());
			groceryItem.setName(groceryAddRequestDTO.getName() != null ? groceryAddRequestDTO.getName() : groceryItem.getName());
			groceryItem.setPrice(groceryAddRequestDTO.getPrice() != null ? groceryAddRequestDTO.getPrice() : groceryItem.getPrice());
			groceryItemRepository.save(groceryItem);
			log.info("AdminServiceImpl updating the grocery data end{} ", groceryItem);

			log.info("AdminServiceImpl converting db data model to response dto start{}");

			response.setStatus(200);
			response.setMessage("Grocery Data Updated successfully!!");
			response.setError(false);
			response.setData(groceryItem);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception exception) {
			log.info("Exception while getting the data : " + exception);
			throw exception;

		}
	}
}
