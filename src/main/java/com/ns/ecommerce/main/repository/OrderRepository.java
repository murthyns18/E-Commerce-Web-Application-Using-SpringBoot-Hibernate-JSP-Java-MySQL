package com.ns.ecommerce.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ns.ecommerce.main.model.Order;
import com.ns.ecommerce.main.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> 
{
    List<Order> findByUser(User user);

}
