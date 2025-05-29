package com.example.warehouse.dto.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ResponseStructure<T>{
    int status;
    String message;
    T data;
}
