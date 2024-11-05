package com.shopping.service;

import java.util.List;

import com.shopping.entity.Orders;

public interface AdminService {

	public List<Orders> getAllOrders();
	
	public void updateStatus(long orderId, String status);
}
