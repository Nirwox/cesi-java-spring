/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesi.spring.Exception;

import com.cesi.spring.model.RetourException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class BadRequest extends RuntimeException {
    private final transient RetourException retour;

    public BadRequest(final String message) {
        super();
        retour = new RetourException(HttpStatus.BAD_REQUEST.value(), message);
    }
}
