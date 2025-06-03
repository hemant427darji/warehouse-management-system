package com.example.warehouse.dto.request;

import java.util.Set;

public record InventoryLocationUpdateRequest(
        String blockId,
        String rackId,
        String selfNo,
        Set<String> unitIds
) {
}
