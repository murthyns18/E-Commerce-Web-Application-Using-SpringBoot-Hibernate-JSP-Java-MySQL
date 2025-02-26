package com.ns.ecommerce.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ns.ecommerce.main.model.User;
import com.ns.ecommerce.main.service.EmailService;
import com.ns.ecommerce.main.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController 
{

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@GetMapping("/login")
	public String showLoginPage() 
	{
		return "login";
	}

	@GetMapping("/register")
	public String showRegisterPage() 
	{
		return "register";
	}

	@PostMapping("/checkLogin")
	public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) 
	{
		User user = userService.authenticate(email, password);

		if (user != null) 
		{
			session.setAttribute("user", user);
			return "redirect:/";  
		} 
		else 
		{
			model.addAttribute("error", "Invalid email or password!");
			return "login";
		}
	}


	@PostMapping("/saveRegisterUser")
	public String registerUser(@RequestParam String name, @RequestParam String email,
			@RequestParam String password, @RequestParam String address, Model model) 
	{
		User newUser = new User();
		newUser.setName(name);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setAddress(address);

		userService.saveUser(newUser);
		
		String subject = "🎉 Welcome to E-Commerce - Happy Shopping!";

		String body = "<html><body>"
				+ "<p style='font-size:16px; color:#333;'>Dear <strong>" + name + "</strong>,</p>"
				+ "<p style='font-size:18px; color:#28a745;'><strong>Welcome to E-Commerce! 🛍️</strong></p>"
				+ "<p style='font-size:16px; color:#555;'>Thank you for registering on our platform. We’re thrilled to have you on board! 🎉</p>"
				+ "<p style='font-size:16px; color:#007bff;'><strong>Explore our latest collections and exclusive deals today!</strong></p>"
				+ "<p style='font-size:16px;'>Start shopping now and enjoy a seamless experience. If you have any questions, feel free to reach out!</p>"
				+ "<p style='color:#555;'>Happy Shopping! 😊</p>"
				+ "<p style='color:#333;'><strong>Best Regards,</strong><br>Narasimhamurthy N S</p>"
				+ "</body></html>";

		emailService.sendEmail(email, subject, body);


		return "redirect:/users/login"; 
	}


	@GetMapping("/logout")
	public String logoutUser(HttpSession session)
	{
		session.invalidate();  
		return "redirect:/";
	}

	@PostMapping("/contactUs")
	@ResponseBody
	public Map<String, String> sendContactUsEmail(@RequestParam String name, 
			@RequestParam String email, 
			@RequestParam String message) 
	{
		String subject = "📩 New Contact Us Inquiry from " + name;

		String body = "<html><body>"
				+ "<p style='font-size:16px; color:#333;'>Dear Admin,</p>"
				+ "<p style='font-size:16px;'>You have received a new inquiry from the Contact Us form:</p>"
				+ "<p style='font-size:16px;'><strong>Name:</strong> " + name + "</p>"
				+ "<p style='font-size:16px;'><strong>Email:</strong> " + email + "</p>"
				+ "<p style='font-size:16px;'><strong>Message:</strong><br> " + message + "</p>"
				+ "<p style='color:#333;'><strong>Best Regards,</strong><br> E-Commerce Website</p>"
				+ "</body></html>";

		String adminEmail = "murthy.ns646@gmail.com"; 
		emailService.sendEmail(adminEmail, subject, body, true); 

		Map<String, String> response = new HashMap<>();
		response.put("success", "Your message has been sent successfully! We will get back to you soon.");

		return response;  
	}


}
