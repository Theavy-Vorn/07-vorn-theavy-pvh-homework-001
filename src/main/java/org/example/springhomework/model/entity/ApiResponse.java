package org.example.springhomework.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private HttpStatus status;
    private T payload;
    private LocalDateTime timestamp;
}
