package com.cgi.grocery.service;

import com.cgi.grocery.model.Grocery;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GroceryServiceTest {
	
	
	@InjectMocks
	GroceryService service;

	@Test
	@DisplayName("unit testing for get groceries by name")
	public void getGroceriesByNameTest() {
		List<Grocery> list = Lists.newArrayList(new Grocery("Amla", 97, "18-07-2012"));
		List<Grocery> data=service.getGroceriesByName(list, "Amla");
		assertEquals(data.get(0).getDate(),"18-07-2012");
	}
}
