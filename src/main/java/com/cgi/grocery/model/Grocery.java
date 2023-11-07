package com.cgi.grocery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grocery {

	private String itemName;

	private float price;

	private String date;

}
