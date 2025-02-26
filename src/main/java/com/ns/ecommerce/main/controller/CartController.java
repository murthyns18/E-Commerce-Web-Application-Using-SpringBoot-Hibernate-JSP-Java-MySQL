package com.ns.ecommerce.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.ecommerce.main.model.Product;
import com.ns.ecommerce.main.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController
{

	private final ProductService productService;

	public CartController(ProductService productService)
	{
		this.productService = productService;
	}

	@PostMapping("/cart/add")
	@ResponseBody
	public Map<String, String> addToCart(@RequestBody Map<String, Integer> request, HttpSession session)
	{
		int productId = request.get("productId");
		System.out.println("Received productId: " + productId);

		if (productId <= 0) {
			return Map.of("status", "error", "message", "Invalid product ID");
		}

		Optional<Product> productOpt = productService.getAllProducts()
				.stream()
				.filter(p -> p.getId() == productId)
				.findFirst();

		if (productOpt.isEmpty()) 
		{
			return Map.of("status", "error", "message", "Product not found");
		}

		Product product = productOpt.get();

		List<Product> cart = (List<Product>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
			session.setAttribute("cart", cart);
		}

		boolean productExists = cart.stream().anyMatch(p -> p.getId() == productId);

		if (!productExists) 
		{
			cart.add(product);
			session.setAttribute("cart", cart);
			return Map.of("status", "success", "message", product.getName() + " added to cart successfully!", "productName", product.getName());
		} 
		else
		{
			return Map.of("status", "info", "message", product.getName() + " already exists in the cart", "productName", product.getName());
		}
	}
	@PostMapping("/cart/remove")
	@ResponseBody
	public Map<String, String> removeFromCart(@RequestBody Map<String, Integer> request, HttpSession session) 
	{
		int productId = request.get("productId");
		List<Product> cart = (List<Product>) session.getAttribute("cart");

		if (cart != null) 
		{
			cart.removeIf(p -> p.getId() == productId);  
			session.setAttribute("cart", cart); 
		}

		return Map.of("status", "success", "message", "Product removed successfully");
	}


	@GetMapping("/cart/count")
	@ResponseBody
	public Map<String, Integer> getCartCount(HttpSession session) 
	{
		List<Product> cart = (List<Product>) session.getAttribute("cart");
		int count = (cart != null) ? cart.size() : 0;
		return Map.of("count", count);
	}

	@GetMapping("/cart")
	public String showCart(HttpSession session, Model model) 
	{
		List<Product> cart = (List<Product>) session.getAttribute("cart");
		model.addAttribute("cart", cart != null ? cart : List.of()); 
		return "cart"; 
	}
}
