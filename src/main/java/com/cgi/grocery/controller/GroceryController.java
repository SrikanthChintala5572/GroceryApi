package com.cgi.grocery.controller;

import com.cgi.grocery.client.GroceryProxy;
import com.cgi.grocery.model.Grocery;
import com.cgi.grocery.service.GroceryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Srikanth Chintala
 * type GroceryController exposes grocery end points
 */
@RestController
public class GroceryController {

	private final GroceryService groceryService;
	@Autowired
	private GroceryProxy groceryProxy;

	@Autowired
	public GroceryController(GroceryService groceryService) {
		super();
		this.groceryService = groceryService;
	}
	@Operation(summary="Gets all sorted grocery items")
	@GetMapping(value="/items", produces = "application/json")
	public ResponseEntity<List<Grocery>> getGroceryItems() throws Exception{
		List<Grocery> groceryList = groceryProxy.getAllGroceryItems();
		List<Grocery> grocerySortedList = groceryService.getGroceryItems(groceryList);
		return ResponseEntity.ok(grocerySortedList);
	}
	@Operation(summary="Gets the distinct grocery names")
	@GetMapping(value="/itemNames", produces = "application/json")
	public ResponseEntity<List<String>> getDistinctGroceryName() throws Exception{
		List<Grocery> groceryList = groceryProxy.getAllGroceryItems();
		List<String> distinctGrocery = groceryService.getDistinctGrocery(groceryList);
		return ResponseEntity.ok(distinctGrocery);
	}

	@Operation(summary="Gets the grocery data by name")
	@GetMapping(value="/item/name",  produces = "application/json")
	public ResponseEntity<List<Grocery>> getGroceriesByName(@RequestParam("itemName") String name) throws Exception {
		List<Grocery> groceryList = groceryProxy.getAllGroceryItems();
		List<Grocery> groceryByName = groceryService.getGroceriesByName(groceryList,name);
		return ResponseEntity.ok(groceryByName);
	}

}
