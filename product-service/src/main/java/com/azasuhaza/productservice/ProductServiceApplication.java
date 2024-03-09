package com.azasuhaza.productservice;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.azasuhaza.productservice.model.Product;
import com.azasuhaza.productservice.repos.ProductRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ProductServiceApplication implements CommandLineRunner {

	private final ProductRepository prodRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if(prodRepo.count() <1) {
			Product product = new Product();
			
			product.setName("frozen croissants");
			product.setDescription("butter croissants");
			product.setPrice(BigDecimal.valueOf(15.99));
			
			prodRepo.save(product);
		}
	}

}
