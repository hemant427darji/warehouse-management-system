package com.example.warehouse.dto.request;

import com.example.warehouse.enums.BlockType;

public record BlockRequest(
        String name,
        double height,
        double length,
        double breath,
        BlockType type
) {
}
