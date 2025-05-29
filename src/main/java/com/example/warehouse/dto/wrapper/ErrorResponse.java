package com.example.warehouse.dto.wrapper;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    int status;
    String errorMessage;
}
