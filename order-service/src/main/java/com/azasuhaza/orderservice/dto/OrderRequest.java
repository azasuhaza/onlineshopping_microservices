package com.azasuhaza.orderservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest {

	private List<ProductItemDTO> productItemDTOList;
}
