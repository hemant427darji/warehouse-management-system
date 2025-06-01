package com.example.warehouse.service.impl;
import com.example.warehouse.dto.mapper.UserMapper;
import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.dto.request.UserRequest;
import com.example.warehouse.dto.response.UserResponse;
import com.example.warehouse.entity.Admin;
import com.example.warehouse.entity.Staff;
import com.example.warehouse.entity.User;
import com.example.warehouse.enums.UserRole;
import com.example.warehouse.exceptions.UnSupportedUserRoleException;
import com.example.warehouse.exceptions.UserNotFoundByIdException;
import com.example.warehouse.exceptions.UserNotLoggedInException;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.example.warehouse.security.AuthUtilities.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserResponse addUser(UserRegistrationRequest urr) {
            UserRole role = urr.userRole();
            User user = switch (urr.userRole()){
                case ADMIN -> userMapper.userToEntity(urr, new Admin());
                case STAFF -> userMapper.userToEntity(urr, new Staff());
                default -> throw new UnSupportedUserRoleException("Unsupported role: " + role);
            };
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return userMapper.userToResponse(user);
    }
    @Override
    public UserResponse updateUser(UserRequest request) {
        User existingUser = getCurrentUsername().map(
                username->userRepository.findByEmail(username)
                        .orElseThrow(()->new UserNotFoundByIdException("User Not Found By Id !!")))
                .orElseThrow(()->new UserNotLoggedInException("User Not Logged In"));

      User user = userMapper.requestToEntity(request,existingUser);
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
      userRepository.save(user);
      return userMapper.userToResponse(user);
    }
    @Override
    public UserResponse findUserById() {
       User user = getCurrentUsername().map(username->userRepository.findByEmail(username).orElseThrow(()->new UserNotFoundByIdException("User Not Found Based On Id!!"))).orElseThrow(()->new UserNotLoggedInException("User Not LoggedIn Yet"));
       return userMapper.userToResponse(user);
    }
    @Override
    public UserResponse deleteUserById(String userId) {
       User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundByIdException("UserId Not Found!!"));
        userRepository.delete(user);
        return userMapper.userToResponse(user);
    }
}
