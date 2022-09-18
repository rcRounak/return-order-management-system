package com.example.authorizationService.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp;

    /**
     * Used only for input validation errors
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> fieldErrors;
}
