package com.ns.ecommerce.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ns.ecommerce.main.model.Order;
import com.ns.ecommerce.main.repository.OrderRepository;

@Service
public class OrderService
{

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> getAllOrders() 
	{
		return orderRepository.findAll();
	}

	public Order getOrderById(Long orderId) 
	{
		return orderRepository.findById(orderId).orElse(null);
	}

	public void updateOrderStatus(Long orderId, String status)
	{
		Order order = orderRepository.findById(orderId).orElse(null);
		if (order != null) 
		{
			order.setStatus(status);
			orderRepository.save(order);
		}
	}
}
