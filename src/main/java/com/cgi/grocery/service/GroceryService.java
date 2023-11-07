package com.cgi.grocery.service;

import com.cgi.grocery.exception.ResourceNotFoundException;
import com.cgi.grocery.model.Grocery;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@EnableFeignClients
public class GroceryService {

	private static final Logger logger = LoggerFactory.getLogger(GroceryService.class);

	XlsxFileReadService xlsxFileReadService;

	public GroceryService(XlsxFileReadService xlsxFileReadService) {
		super();
		this.xlsxFileReadService = xlsxFileReadService;
	}

	public List<Grocery> getGroceryItems(List<Grocery> groceryList) {
		return getSortedGroceryData(groceryList);
	}

	public List<Grocery> getGroceriesByName(List<Grocery> groceryList,String name) {
		List<Grocery> list = groceryList.parallelStream()
				.filter(item -> item.getItemName().matches("(.*)" + name + "(.*)")).collect(Collectors.toList());
		if (list.isEmpty()) {
			logger.error("No Item found with " + name);
			throw new ResourceNotFoundException("No Item found with " + name);
		}
		return list;
	}

	public List<String> getDistinctGrocery(List<Grocery> groceryList) {
		Set<String> set = new HashSet<>();
		List<Grocery> sortedList = getGroceryItems(groceryList);
		List<Grocery> distinctList = sortedList.parallelStream()
				.filter(item -> set.add(item.getItemName())).collect(Collectors.toList());
		List<String> names = distinctList.stream().map(Grocery::getItemName).collect(Collectors.toList());
		return names;
	}
	private List<Grocery> getSortedGroceryData(List<Grocery> data) {
		return data.parallelStream().filter(item -> !item.getItemName().equals(""))
				.sorted(Comparator.comparing(Grocery::getItemName)
						.thenComparing(Grocery::getPrice, Comparator.reverseOrder())
						.thenComparing(Grocery::getDate, Comparator.reverseOrder()))
				.collect(Collectors.toList());
	}
}
