package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.UserMapper;
import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.dto.response.UserResponse;
import com.example.warehouse.entity.Admin;
import com.example.warehouse.entity.Staff;
import com.example.warehouse.entity.User;
import com.example.warehouse.enums.UserRole;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse addUser(UserRegistrationRequest urr) {

            UserRole role = UserRole.valueOf(urr.userRole().toUpperCase());
            User user;
            if (role == UserRole.STAFF) {
                user = new UserMapper().userToEntity(urr, new Staff());
            } else if (role == UserRole.ADMIN) {
                user = new UserMapper().userToEntity(urr, new Admin());
            } else {
                throw new IllegalArgumentException("Unsupported role: " + role);
            }
            userRepository.save(user);
            return new UserMapper().userToResponse(user);
    }
}
