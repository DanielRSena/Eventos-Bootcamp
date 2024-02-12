package com.danielrsena.medicinesAPI.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/inicio")
public class MedicineController {
    
    @GetMapping
    public String helloWord(){
        return "Hello, World!";
    }
}
