package com.ns.ecommerce.main.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ns.ecommerce.main.model.Discount;
import com.ns.ecommerce.main.model.Product;
import com.ns.ecommerce.main.repository.DiscountRepository;
import com.ns.ecommerce.main.service.ProductService;

@Controller 
public class HomeController 
{

    @Autowired
    private ProductService productService;
    
    @Autowired
    private DiscountRepository discountRepository;

    @GetMapping("/") 
    public String homePage(Model model)
    {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        
        List<Discount> discounts = discountRepository.findAll();
        model.addAttribute("discounts", discounts);
        
        return "home";
    }
}
