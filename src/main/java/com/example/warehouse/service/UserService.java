package com.example.warehouse.service;

import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    void addUser(UserRegistrationRequest user);
}
