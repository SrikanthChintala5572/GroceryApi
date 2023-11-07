package com.cgi.grocery.client;

import com.cgi.grocery.model.Grocery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(name="grocery-data-provider")
public interface GroceryProxy {

    @GetMapping("/groceryData")
    public List<Grocery> getAllGroceryItems();

}