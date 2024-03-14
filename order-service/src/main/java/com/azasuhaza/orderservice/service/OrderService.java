package com.azasuhaza.orderservice.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.azasuhaza.orderservice.dto.OrderRequest;
import com.azasuhaza.orderservice.model.MyOrder;

@Service
public class OrderService {

	
	public void placeOrder(OrderRequest orderRequest) {
		MyOrder order= new MyOrder();
		
		order.setOrderNumber(UUID.randomUUID().toString());
		order.setTotalDiscounts((long) 5.00);
		order.setTotalSales(new BigDecimal("300.00"));
		order.setTotalTax((long) 2.9);
		order.setItemsPurchased(null);
	}
}
