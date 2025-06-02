package com.example.warehouse.dto.request;

public record ProductRequest(
        String productTitle,
        double productWeight,
        double productLength,
        double productHeight,
        double productWidth,
        String materialType,
        String careInstruction,
        int quantity,
        double productPrice
) {
}
