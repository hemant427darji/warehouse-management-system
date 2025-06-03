package com.example.warehouse.service.contract;

import com.example.warehouse.dto.request.InBoundBatchRequest;
import com.example.warehouse.dto.response.InBoundBatchResponse;

public interface InBoundBatchServive {
    InBoundBatchResponse receiveProductUnit(InBoundBatchRequest request, String shipmentId);
}
