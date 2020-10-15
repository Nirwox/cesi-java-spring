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
public class NotFound extends RuntimeException {
    private final transient Retour retour;

    public NotFound(final String message) {
        super();
        retour = new Retour(HttpStatus.NOT_FOUND.value(), message);
    }
}
