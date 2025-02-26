package com.ns.ecommerce.main.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private String productNames;
	private LocalDate orderDate;
	private int totalAmount;
	private String status;
	private String paymentMode;

	public Order() {}

	public Order(User user, List<Product> products, int totalAmount, String paymentMode, String status)
	{
		this.user = user;
		this.productNames = products.stream().map(Product::getName).collect(Collectors.joining(", "));
		this.totalAmount = totalAmount;
		this.paymentMode = paymentMode;
		this.status = status;
		this.orderDate = LocalDate.now();
	}

	public Long getOrderId()
	{
		return orderId;
	}

	public void setOrderId(Long orderId) 
	{
		this.orderId = orderId;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}

	public String getProductNames()
	{
		return productNames;
	}

	public void setProductNames(String productNames) 
	{
		this.productNames = productNames;
	}

	public LocalDate getOrderDate()
	{
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) 
	{
		this.orderDate = orderDate;
	}

	public double getTotalAmount() 
	{
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount)
	{
		this.totalAmount = totalAmount;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getPaymentMode() 
	{
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) 
	{
		this.paymentMode = paymentMode;
	}
}
