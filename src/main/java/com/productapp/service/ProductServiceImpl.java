package com.productapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productapp.exceptions.InvalidIdException;
import com.productapp.exceptions.ProductNotFoundException;
import com.productapp.model.Product;
import com.productapp.model.ProductDto;
import com.productapp.model.ProductMapper;
import com.productapp.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	private IProductRepository productRepository;
	@Autowired
	private ProductMapper productMapper;
	
	public ProductServiceImpl(IProductRepository productRepository) {
		
		this.productRepository = productRepository;
	}

	@Override
	public void addProduct(ProductDto productDto) {
	
		Product product = productMapper.convertToProduct(productDto);
		productRepository.insert(product);
	}

	@Override
	public void updateProduct(ProductDto productDto) {
		
		Product product = productMapper.convertToProduct(productDto);
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(String productId) {
		
		productRepository.deleteById(productId);
	}

	@Override
	public List<ProductDto> getAll() {
		
		List<Product> products = productRepository.findAll();
		List<ProductDto> productDtos = products.stream()
							.map(product->productMapper.convertToDto(product)).toList();
		return productDtos;
	}

	@Override
	public ProductDto getById(String productId) throws InvalidIdException {
		Product product = productRepository.findById(productId)
				.orElseThrow(()-> new InvalidIdException("Invalid Id"));
		return productMapper.convertToDto(product);
	}

	@Override
	public List<ProductDto> getByBrand(String brand) throws ProductNotFoundException {
		
		List<Product> products = productRepository.findByBrand(brand);
		if(products.isEmpty())
			throw new ProductNotFoundException("Product with this brand is not available");
		List<ProductDto> productDtos = products.stream()
							.map(product->productMapper.convertToDto(product)).toList();
		return productDtos;
	}

	@Override
	public List<ProductDto> getByCategory(String[] category) throws ProductNotFoundException {

		List<Product> products = productRepository.findByCategory(category);
		if(products.isEmpty())
			throw new ProductNotFoundException("Product with this Category is not available");
		List<ProductDto> productDtos = products.stream()
							.map(product->productMapper.convertToDto(product)).toList();
		return productDtos;
	}

	@Override
	public List<ProductDto> getByColor(String color) throws ProductNotFoundException {
		
		List<Product> products = productRepository.findByFeaturesColor(color);
		if(products.isEmpty())
			throw new ProductNotFoundException("Product with this Color is not available");
		List<ProductDto> productDtos = products.stream()
							.map(product->productMapper.convertToDto(product)).toList();
		return productDtos;
	}

	@Override
	public List<ProductDto> getByBrandAndLesserPrice(String brand, double price) throws ProductNotFoundException {

		List<Product> products = productRepository.findByBrandAndPriceLessThan(brand, price);
		if(products.isEmpty())
			throw new ProductNotFoundException("Product with this brand and price is not available");
		List<ProductDto> productDtos = products.stream()
							.map(product->productMapper.convertToDto(product)).toList();
		return productDtos;
	}

}
