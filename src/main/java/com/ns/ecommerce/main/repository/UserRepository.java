package com.ns.ecommerce.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ns.ecommerce.main.model.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmailAndPassword(String email, String password);
}
