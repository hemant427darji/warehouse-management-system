package com.example.warehouse.dto.response;

public record ProductDetailsResponse(
        String productId,
        String title,
        String type,
        double price,
        long Quantity
) {
}
