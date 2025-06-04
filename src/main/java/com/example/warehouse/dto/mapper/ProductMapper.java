package com.example.warehouse.dto.mapper;

import com.example.warehouse.dto.response.ProductDetailsResponse;
import com.example.warehouse.entity.Product;
import org.springframework.stereotype.Controller;

@Controller
public class ProductMapper {

    public ProductDetailsResponse toResponse(Product product,long Quantity){
        return new ProductDetailsResponse(
                product.getProductId(),
                product.getTitle(),
                product.getMaterialType(),
                product.getPrice(),
                Quantity
        );
    }
}
