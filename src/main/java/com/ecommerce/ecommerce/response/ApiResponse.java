package com.ecommerce.ecommerce.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    String message;
    Object data;
}
