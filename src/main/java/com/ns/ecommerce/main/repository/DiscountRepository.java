package com.ns.ecommerce.main.repository;

import com.ns.ecommerce.main.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long>
{
    List<Discount> findAll(); 
    Discount findTopByOrderByIdDesc();
}