package com.ns.ecommerce.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ns.ecommerce.main.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> 
{
	
}
