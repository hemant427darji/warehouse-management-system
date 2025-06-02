package com.example.warehouse.dto.response;

import com.example.warehouse.enums.InBoundStatus;

public record InBoundShipmentResponse(
        String shipmentId,
        ProductResponse productResponse,
        String sellerId,
        long createdAt,
        InBoundStatus status
) {
}