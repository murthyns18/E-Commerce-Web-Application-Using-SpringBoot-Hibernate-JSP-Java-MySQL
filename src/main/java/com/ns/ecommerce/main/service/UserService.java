package com.ns.ecommerce.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ns.ecommerce.main.model.User;
import com.ns.ecommerce.main.repository.UserRepository;

@Service
public class UserService 
{

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    // Authenticate user by email & password
    public User authenticate(String email, String password)
    {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
