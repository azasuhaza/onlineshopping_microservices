package com.azasuhaza.productservice.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.azasuhaza.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
