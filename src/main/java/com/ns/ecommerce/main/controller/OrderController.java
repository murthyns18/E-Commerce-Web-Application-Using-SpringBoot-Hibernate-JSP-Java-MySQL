package com.ns.ecommerce.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ns.ecommerce.main.model.Order;
import com.ns.ecommerce.main.model.Product;
import com.ns.ecommerce.main.model.User;
import com.ns.ecommerce.main.repository.OrderRepository;
import com.ns.ecommerce.main.service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController 
{

	private final OrderRepository orderRepository;

	@Autowired
	private EmailService emailService;

	public OrderController(OrderRepository orderRepository) 
	{
		this.orderRepository = orderRepository;
	}


	@PostMapping("/place")
	public String placeOrder(@RequestParam("payment") String paymentMode, HttpSession session) 
	{
		User user = (User) session.getAttribute("user");
		if (user == null) 
		{
			session.setAttribute("error", "Please log in before placing an order.");
			return "redirect:/users/login";
		}

		List<Product> cart = (List<Product>) session.getAttribute("cart");
		if (cart == null || cart.isEmpty()) 
		{
			session.setAttribute("error", "Your cart is empty!");
			return "redirect:/cart";
		}

		int totalAmount = (int) cart.stream().mapToDouble(Product::getPrice).sum();

		Order order = new Order(user, cart, totalAmount, paymentMode, "Pending");
		orderRepository.save(order);

		session.setAttribute("lastOrder", order);
		session.removeAttribute("cart");

		String subject = "🎉 Order Confirmation - Thank You for Shopping!";

		StringBuilder body = new StringBuilder();
		body.append("<html><body>")
		.append("<p style='font-size:16px; color:#333;'>Dear <strong>").append(user.getName()).append("</strong>,</p>")
		.append("<p style='color:#28a745; font-size:18px;'><strong>Your order has been placed successfully! ✅</strong></p>")
		.append("<p style='font-size:16px; color:#555;'>Here are your order details:</p>")
		.append("<ul style='list-style:none; padding:0;'>");

		for (Product product : cart) 
		{
			body.append("<li style='color:#007bff; font-size:16px;'>📦 <strong>")
			.append(product.getName())
			.append("</strong> - <span style='color:#e63946;'>₹")
			.append(product.getPrice())
			.append("</span></li>");
		}

		body.append("</ul>")
		.append("<p style='font-size:16px;'><strong>Total Amount: </strong> <span style='color:#e63946; font-size:18px;'>₹")
		.append(totalAmount)
		.append("</span></p>")
		.append("<p style='font-size:16px;'>As a token of appreciation, here’s a <strong style='color:#ff9800;'>10% OFF</strong> coupon for your next purchase: <strong style='background:#ff9800; color:#fff; padding:5px 10px; border-radius:5px;'>THANKYOU10</strong> 🎉</p>")
		.append("<p style='color:#555;'>Thank you for shopping with us! 😊</p>")
		.append("<p style='color:#333;'><strong>Best Regards,</strong><br>Narasimhamurthy N S</p>")
		.append("</body></html>");

		emailService.sendEmail(user.getEmail(), subject, body.toString());

		return "redirect:/order/confirmation";
	}

	@GetMapping("/confirmation")
	public String showConfirmation(HttpSession session)
	{
		session.setAttribute("message", "Your order has been placed successfully!");

		Order order = (Order) session.getAttribute("lastOrder");
		if (order != null) 
		{
			session.setAttribute("order", order);
		}

		return "order_confirmation";
	}

	@GetMapping("/checkout")
	public String checkout(HttpSession session) 
	{
		return "checkout";
	}

	@GetMapping("/history")
	public String orderHistory(HttpSession session, Model model) 
	{
		User user = (User) session.getAttribute("user");

		if (user == null) {
			session.setAttribute("error", "Please log in to view your order history.");
			return "redirect:/users/login";
		}

		List<Order> orders = orderRepository.findByUser(user);
		model.addAttribute("orders", orders);

		return "order_history";
	}

}
