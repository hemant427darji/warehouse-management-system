package com.example.warehouse.controller;
import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.dto.request.UserRequest;
import com.example.warehouse.dto.response.UserResponse;
import com.example.warehouse.dto.wrapper.ResponseStructure;
import com.example.warehouse.service.contract.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody @Valid UserRegistrationRequest urr){
       UserResponse ur = userService.addUser(urr);
       ResponseStructure<UserResponse> responseStructure = new ResponseStructure(HttpStatus.CREATED.value(),"User Successfully Added Into the Database",ur);
       return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
    }
    @PutMapping("/profile")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest request){
        UserResponse userResponse = userService.updateUser(request);
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Update Details Update Successfully",userResponse);

        return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
    }
    @GetMapping("/profile")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(){
        UserResponse userResponse = userService.findUserById();
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "User Find By Respected Id",userResponse);
        return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/profile")
    public ResponseEntity<ResponseStructure<UserResponse>> deleteStaffById(@PathVariable String userId){
        UserResponse userResponse = userService.deleteUserById(userId);
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "User Deleted!!",userResponse);
        return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
    }
}
