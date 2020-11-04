package com.cesi.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetourException {
    private int code;
    private String message;

    public RetourException(int code) {
        this.code = code;
    }

    public RetourException(String message) {
        this.message = message;
    }
}
