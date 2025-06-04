package com.example.warehouse.service.contract;


import com.example.warehouse.dto.response.ProductDetailsResponse;

public interface FetchProductService {
    ProductDetailsResponse fetchProductQuantity(String productId);
}
