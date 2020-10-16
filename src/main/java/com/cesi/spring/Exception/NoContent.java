/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesi.spring.exception;

import com.cesi.spring.model.Retour;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoContent extends RuntimeException {
    private final transient Retour retour;

    public NoContent(final String message) {
        super();
        retour = new Retour(HttpStatus.NO_CONTENT.value(), message);
    }
}
