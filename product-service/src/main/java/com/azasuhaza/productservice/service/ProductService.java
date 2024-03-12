package com.azasuhaza.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import com.azasuhaza.productservice.dto.ProductRequest;
import com.azasuhaza.productservice.dto.ProductResponse;
import com.azasuhaza.productservice.exceptions.ProductControllerException;
import com.azasuhaza.productservice.exceptions.ProductServiceException;
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
		
		if(product.equals(null)) {
			throw new ProductServiceException("201","ProductService exception- product is blank");
		}
		
		try {
			prodRepo.save(product);
			log.info("Product {} is saved", product.getId());	
		} catch (HttpMessageNotReadableException e) {
			throw new ProductServiceException("202","Required request body is missing " + e.getMessage());
		}catch (IllegalArgumentException e) {
			throw new ProductServiceException("203","ProductService exception- null " + e.getMessage());
		} catch (Exception e) {
			throw new ProductServiceException("204","ProductService exception- soemthing wrong " + e.getMessage());
		}

	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = null;
		
		try {
			products = prodRepo.findAll();
		} catch (Exception e) {
			throw new ProductServiceException("205","ProductService exception- error fetching all products " + e.getMessage());
		}

		if(products.isEmpty()) {
			throw new ProductServiceException("206","ProductService exception- products is empty ");
		}
		
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

	public ProductResponse getProductById(String id) {
		
		try {
			Product product = prodRepo.findById(id).get();
			return mapToProductResponse(product);
		} catch(IllegalArgumentException e) {
			
			throw new ProductServiceException("207","ProductService exception- products " + id + " not exist");
		} catch (java.util.NoSuchElementException e) {
			throw new ProductServiceException("208","given employee id [" + id+"] doesnot exist in DB" + e.getMessage());
		}catch(Exception e) {
			throw new ProductServiceException("209","ProductService exception- soemthing wrong");
		}
		
		
	}
	
	
}





