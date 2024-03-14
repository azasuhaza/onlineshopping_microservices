package com.azasuhaza.orderservice.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="os_orders")
@Data
@AllArgsConstructor
public class MyOrder {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)	
	private Long id;
	private String orderNumber;
	
	@OneToMany(cascade= CascadeType.ALL)
	private List<Product> itemsPurchased;
	
	private BigDecimal totalSales;
	private Long totalTax;
	private Long totalDiscounts;

	public MyOrder() {
	}
}
