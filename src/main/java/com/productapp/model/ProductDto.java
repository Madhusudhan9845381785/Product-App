package com.productapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	
	private String productName;
	private String productId;
	private Features features;
	private String brand;
	private String[] category;
	private double price;
}
