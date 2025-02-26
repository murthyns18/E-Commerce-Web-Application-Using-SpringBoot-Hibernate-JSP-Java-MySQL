package com.ns.ecommerce.main.service;

import java.util.List;
import com.ns.ecommerce.main.model.Product;

public interface ProductService 
{
    List<Product> getAllProducts();
    Product saveProduct(Product product);
    Product getProductById(Long id); 
    public void deleteProductById(Long id);
}
