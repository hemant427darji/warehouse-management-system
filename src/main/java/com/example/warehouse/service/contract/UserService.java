package com.example.warehouse.service.contract;

import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.dto.response.UserResponse;


public interface UserService {
    UserResponse addUser(UserRegistrationRequest user);
}
