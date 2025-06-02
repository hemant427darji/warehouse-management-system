package com.example.warehouse.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlockNotFoundException extends RuntimeException {
    private final String message;
}
