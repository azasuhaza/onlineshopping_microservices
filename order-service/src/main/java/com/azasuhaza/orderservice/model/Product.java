package com.azasuhaza.orderservice.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="os_orders_product")
@Data
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)	
	private Long id;
	
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
	
	
}
