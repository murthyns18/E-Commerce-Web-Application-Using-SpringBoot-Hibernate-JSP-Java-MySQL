package com.ns.ecommerce.main.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ns.ecommerce.main.model.Discount;
import com.ns.ecommerce.main.model.Order;
import com.ns.ecommerce.main.model.Product;
import com.ns.ecommerce.main.service.DiscountService;
import com.ns.ecommerce.main.service.EmailService;
import com.ns.ecommerce.main.service.FileUploadService;
import com.ns.ecommerce.main.service.OrderService;
import com.ns.ecommerce.main.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController 
{

	@Autowired
	private ProductService productService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private DiscountService discountService;

	@GetMapping("/login")
	public String showAdminLoginPage()
	{
		return "adminLogin";
	}

	@PostMapping("/checkLogin")
	public String checkAdminLogin(@RequestParam String username, @RequestParam String password, HttpSession session,
			Model model)
	{
		if ("murthy".equals(username) && "321".equals(password))
		{
			session.setAttribute("admin", username);
			return "redirect:/admin/dashboard";
		} else {
			model.addAttribute("error", "Invalid username or password!");
			return "adminLogin";
		}
	}

	@GetMapping("/dashboard")
	public String adminPage(HttpSession session, Model model)
	{
		if (session.getAttribute("admin") == null)
		{
			return "redirect:/admin/login";
		}
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "admin";
	}

	@PostMapping("/addProduct")
	public String addProduct(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("price") int price, @RequestParam("image") MultipartFile imageFile,
			RedirectAttributes redirectAttributes, HttpSession session) throws IOException
	{

		if (session.getAttribute("admin") == null)
		{
			return "redirect:/admin/login";
		}

		String imageUrl = fileUploadService.uploadImage(imageFile);

		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setImageUrl(imageUrl);

		productService.saveProduct(product);

		redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");

		return "redirect:/admin/dashboard";
	}

	@GetMapping("/logout")
	public String logoutUser(HttpSession session) 
	{
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/addProduct")
	public String addProductPage(HttpSession session)
{
		if (session.getAttribute("admin") == null) 
		{
			return "redirect:/admin/login";
		}
		return "addProduct";
	}

	@GetMapping("/orders")
	public String viewOrders(HttpSession session, Model model) 
	{
		if (session.getAttribute("admin") == null)
		{
			return "redirect:/admin/login";
		}

		List<Order> orders = orderService.getAllOrders();
		model.addAttribute("orders", orders);
		return "admin_order_management";
	}

	@PostMapping("/shipOrder")
	public String shipOrders(@RequestParam("orderId") Long orderId, HttpSession session) 
	{
		if (session.getAttribute("admin") == null)
		{
			return "redirect:/admin/login";
		}

		Order order = orderService.getOrderById(orderId);
		if (order != null)
{
			orderService.updateOrderStatus(orderId, "Shipped");

			String subject = "📦 Your Order Has Been Shipped! 🚚";
			String email = order.getUser().getEmail();

			String body = "<html><body>" + "<p style='font-size:16px; color:#333;'>Dear <strong>"
					+ order.getUser().getName() + "</strong>,</p>"
					+ "<p style='font-size:18px; color:#28a745;'><strong>Great news! Your order is on the way! 🚚</strong></p>"
					+ "<p style='font-size:16px; color:#555;'>Your order (Order ID: <strong>" + orderId
					+ "</strong>) has been shipped and will arrive soon. 🎉</p>"
					+ "<p style='font-size:16px; color:#007bff;'>You can track your order from your account.</p>"
					+ "<p style='font-size:16px;'>We appreciate your trust and hope you love your purchase! If you have any questions, feel free to reach out.</p>"
					+ "<p style='color:#555;'>Thank you for shopping with us! 😊</p>"
					+ "<p style='color:#333;'><strong>Best Regards,</strong><br>Narasimhamurthy N S</p>"
					+ "</body></html>";

			emailService.sendEmail(email, subject, body, true);

		}

		return "redirect:/admin/orders";
	}

	@PostMapping("/updateProduct")
	public String updateProduct(@RequestParam("id") Long id, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") int price,
			RedirectAttributes redirectAttributes, HttpSession session)
	{

		if (session.getAttribute("admin") == null)
		{
			return "redirect:/admin/login";
		}

		Product product = productService.getProductById(id);
		if (product == null) 
		{
			redirectAttributes.addFlashAttribute("errorMessage", "Product not found!");
			return "redirect:/admin/dashboard";
		}

		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);

		productService.saveProduct(product);

		redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");

		return "redirect:/admin/dashboard";
	}

	@GetMapping("/editProduct")
	public String editProductPage(@RequestParam("id") Long id, Model model, HttpSession session) 
	{
		if (session.getAttribute("admin") == null)
		{
			return "redirect:/admin/login";
		}

		Product product = productService.getProductById(id);
		if (product == null) 
		{
			model.addAttribute("errorMessage", "Product not found!");
			return "redirect:/admin/dashboard";
		}

		model.addAttribute("product", product);
		return "editProduct";
	}

	@PostMapping("/removeProduct")
	public String removeProduct(@RequestParam("id") Long id, RedirectAttributes redirectAttributes,
			HttpSession session)
	{

		if (session.getAttribute("admin") == null)
		{
			return "redirect:/admin/login";
		}

		productService.deleteProductById(id);

		redirectAttributes.addFlashAttribute("successMessage", "Product removed successfully!");

		return "redirect:/admin/dashboard";
	}

	@GetMapping("/discount")
	public String getDiscountPage(Model model, HttpSession session)
	{
		model.addAttribute("discount", discountService.getDiscount());

		String successMessage = (String) session.getAttribute("successMessage");
		if (successMessage != null) 
		{
			model.addAttribute("successMessage", successMessage);
			session.removeAttribute("successMessage");
		}
		if (session.getAttribute("admin") == null)
		{
			return "redirect:/admin/login";
		}

		return "discount";
	}

	@PostMapping("/updateDiscount")
	public String updateDiscount(@ModelAttribute Discount discount,
			@RequestParam("offerImage") MultipartFile offerImage, HttpSession session)
	{
		discountService.saveOrUpdateDiscount(discount, offerImage);
		session.setAttribute("successMessage", "Discount updated successfully!");
		return "redirect:/admin/discount";
	}

	@PostMapping("/addDiscount")
	public String addDiscount(@ModelAttribute Discount discount, 
	                          @RequestParam("offerImage") MultipartFile offerImage, 
	                          HttpSession session)
	{
	    discountService.saveOrUpdateDiscount(discount, offerImage);
	    session.setAttribute("successMessage", "Discount added successfully!");
	    return "redirect:/admin/discount";
	}
}
