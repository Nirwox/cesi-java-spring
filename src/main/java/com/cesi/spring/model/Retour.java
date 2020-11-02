package com.cesi.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Retour {
    private int code;
    private String message;

    public Retour(int code) {
        this.code = code;
    }

    public Retour(String message) {
        this.message = message;
    }
}
