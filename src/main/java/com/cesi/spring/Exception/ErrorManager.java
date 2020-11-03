/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesi.spring.Exception;

import com.cesi.spring.Exception.NoContent;
import com.cesi.spring.Exception.NoContent;
import com.cesi.spring.Exception.NotFound;
import com.cesi.spring.Exception.NotFound;
import com.cesi.spring.model.Retour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorManager {
    @ResponseBody
    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Retour notFound(final NotFound ex) {
        return ex.getRetour();
    }
    
    @ResponseBody
    @ExceptionHandler(Conflict.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    Retour conflict(final Conflict ex) {
        return ex.getRetour();
    }
    
    @ResponseBody
    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Retour badRequest(final BadRequest ex) {
        return ex.getRetour();
    }
    
    @ResponseBody
    @ExceptionHandler(NoContent.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Retour noContent(final NoContent ex) {
        return ex.getRetour();
    }
    
}
