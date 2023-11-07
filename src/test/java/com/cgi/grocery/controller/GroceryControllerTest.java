package com.cgi.grocery.controller;

import com.cgi.grocery.client.GroceryProxy;
import com.cgi.grocery.model.Grocery;
import com.cgi.grocery.service.GroceryService;
import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(GroceryController.class)
public class GroceryControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	GroceryController groceryController;
	@MockBean
	GroceryProxy groceryProxy;
	@MockBean
	GroceryService groceryservice;

	@Test
	@DisplayName("Unit test for get groceries by name")
	public void getGroceriesByNameTest() throws Exception {
		List<Grocery> list = Lists.newArrayList(new Grocery("Amla", 97, "18-07-2012"));
		when(groceryservice.getGroceriesByName(eq(list), anyString())).thenReturn(list);
		mockMvc.perform(get("/item/name").param("itemName", "Amla")).andExpect(status().isOk()).andReturn();
	}

}
