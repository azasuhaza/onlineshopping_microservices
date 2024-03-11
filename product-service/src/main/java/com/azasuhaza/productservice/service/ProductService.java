package com.azasuhaza.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.azasuhaza.productservice.dto.ProductRequest;
import com.azasuhaza.productservice.dto.ProductResponse;
import com.azasuhaza.productservice.model.Product;
import com.azasuhaza.productservice.repos.ProductRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductRepository prodRepo;
	
	public void createProduct(ProductRequest productRequest) {
		
		Product product=Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		
		prodRepo.save(product);
		log.info("Product {} is saved", product.getId());
	}

	public List<ProductResponse> getAllProducts() {
		
		List<Product> products = prodRepo.findAll();
		return  products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
	}

	private ProductResponse mapToProductResponse(Product prod) {
	
		return ProductResponse.builder()
				.id(prod.getId())
				.name(prod.getName())
				.description(prod.getDescription())
				.price(prod.getPrice())
				.build();
	}
	
	
}





