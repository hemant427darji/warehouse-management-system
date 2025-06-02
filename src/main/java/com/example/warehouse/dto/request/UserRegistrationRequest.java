package com.example.warehouse.dto.request;

import com.example.warehouse.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRegistrationRequest(
        @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Username can only contain alphabets, numbers, and with allowed _ (underscore) and - (hyphen).")
        String username,
        @NotNull(message = "email cannot be null.")
        @NotBlank(message = "email cannot be blank.")
        @Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "invalid Gmail Id.")
        String email,
        String password,
        UserRole userRole
) { }
