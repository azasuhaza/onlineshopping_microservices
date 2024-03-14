package com.azasuhaza.orderservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductItemDTO {
	
	private Long id;
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
}
