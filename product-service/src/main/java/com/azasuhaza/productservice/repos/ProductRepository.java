package com.azasuhaza.productservice.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.azasuhaza.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

}
