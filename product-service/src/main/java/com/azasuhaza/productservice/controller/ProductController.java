package com.azasuhaza.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.azasuhaza.productservice.dto.ProductRequest;
import com.azasuhaza.productservice.dto.ProductResponse;
import com.azasuhaza.productservice.exceptions.DAOLayerException;
import com.azasuhaza.productservice.exceptions.ProductControllerException;
import com.azasuhaza.productservice.exceptions.ProductServiceException;
import com.azasuhaza.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		try {
			productService.createProduct(productRequest);
		} catch(ProductServiceException e) {
			new ProductControllerException(e.getErrorCode(), e.getErrorMessage());
		}catch(Exception e) {
			new ProductControllerException("102", "Controller exception- not able to create product " + e.getMessage());
		}
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts(){
		try {
			return productService.getAllProducts();
		} catch(ProductServiceException e) {
			new ProductControllerException(e.getErrorCode(), e.getErrorMessage());
		}catch (Exception ex){
			new ProductControllerException("103", "Controller exception- not able to fetch all products " + ex.getMessage());
		}
		return null;
		
	}
	
	@GetMapping("/{productid}")
	public ResponseEntity<?> getProductById(@PathVariable("productid") String id){

		//custom exception handling
//		try {
//			ProductResponse pr = productService.getProductById(id);
//			return new ResponseEntity<ProductResponse>(pr, HttpStatus.OK);
//		}catch(ProductServiceException e) {
//			ProductControllerException pce= new ProductControllerException(e.getErrorCode(), e.getErrorMessage());
//			return new ResponseEntity<ProductControllerException>(pce, HttpStatus.BAD_REQUEST);
//		}catch(DAOLayerException e) {
//			DAOLayerException pce= new DAOLayerException(e.getErrorCode(), e.getErrorMessage());
//			return new ResponseEntity<DAOLayerException>(pce, HttpStatus.NOT_FOUND);
//		} catch(Exception e) {
//			ProductControllerException pce= 
//					new ProductControllerException("104", "Controller exception- not able to fetch product id [ " + id + " ]"
//			+ e.getMessage() + " " + e.getCause());
//			
//			return new ResponseEntity<ProductControllerException>(pce, HttpStatus.BAD_GATEWAY);
//		}
		
		//global exception handling
		ProductResponse pr = productService.getProductById(id);
		return new ResponseEntity<ProductResponse>(pr, HttpStatus.OK);		
		
	}
	
}






