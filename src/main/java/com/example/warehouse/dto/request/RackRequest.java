package com.example.warehouse.dto.request;

public record RackRequest(
        String name,
        double height,
        double width,
        double breath
) {
}
