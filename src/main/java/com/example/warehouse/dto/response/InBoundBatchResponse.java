package com.example.warehouse.dto.response;

import com.example.warehouse.entity.ProductUnit;

import java.util.List;

public record InBoundBatchResponse(
        String batchId,
        int countOfRejectedUnit,
        int countOfAcceptedUnit
) {
}
