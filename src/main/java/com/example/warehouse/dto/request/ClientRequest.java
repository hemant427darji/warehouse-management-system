package com.example.warehouse.dto.request;

import jakarta.validation.constraints.Email;

public record ClientRequest(
        String OrganizationName,
        String email
) {
}
