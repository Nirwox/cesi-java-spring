/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesi.spring.Exception;

import com.cesi.spring.model.Retour;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class Conflict extends RuntimeException {
    private final transient Retour retour;

    public Conflict(final String message) {
        super();
        retour = new Retour(HttpStatus.CONFLICT.value(), message);
    }
}
