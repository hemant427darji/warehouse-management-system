package com.example.warehouse.dto.response;

public record RackResponse(
        String rackId,
        String name,
        double height,
        double width,
        double breath
) {
}
