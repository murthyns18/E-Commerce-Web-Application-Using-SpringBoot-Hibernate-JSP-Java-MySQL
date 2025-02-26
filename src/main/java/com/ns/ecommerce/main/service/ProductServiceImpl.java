package com.ns.ecommerce.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ns.ecommerce.main.model.Product;
import com.ns.ecommerce.main.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService 
{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() 
	{
		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) 
	{
		return productRepository.save(product);
	}

	@Override
	public Product getProductById(Long id) 
	{
		Optional<Product> optionalProduct = productRepository.findById(id);
		return optionalProduct.orElse(null); // Returns product or null if not found
	}

	public void deleteProductById(Long id)
	{
		productRepository.deleteById(id);
	}
}
