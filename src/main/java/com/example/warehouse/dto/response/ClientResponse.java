package com.example.warehouse.dto.response;

public record ClientResponse(
        String clientId,
        String OrganizationName,
        String email,
        String apiKey,
        String secretKey,
        long registerAt
) {
}
