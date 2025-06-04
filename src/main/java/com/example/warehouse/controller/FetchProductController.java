package com.example.warehouse.controller;

import com.example.warehouse.dto.response.ProductDetailsResponse;
import com.example.warehouse.dto.wrapper.ResponseStructure;
import com.example.warehouse.service.contract.FetchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FetchProductController {

    @Autowired
    private FetchProductService fetchProductService;

    @GetMapping("/QuantityOf")
    public ResponseEntity<ResponseStructure<ProductDetailsResponse>> findProductQuantity(@RequestParam String productId){
       ProductDetailsResponse productQuantity = fetchProductService.fetchProductQuantity(productId);
       ResponseStructure<ProductDetailsResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Product Details",productQuantity);
       return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
    }
}
