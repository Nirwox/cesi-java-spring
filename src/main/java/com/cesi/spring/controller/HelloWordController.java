package com.cesi.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hugo-Louvet
 */
@RestController
@RequestMapping("/rest/1")
public class HelloWordController {
    @RequestMapping("/")
    public String index() {
            return "Greetings from Spring Boot!";
    }
}
