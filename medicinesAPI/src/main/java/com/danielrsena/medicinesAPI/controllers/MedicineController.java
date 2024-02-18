package com.danielrsena.medicinesAPI.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrsena.medicinesAPI.dto.MedicineDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController //indica ao String que deve iniciá-la como um controller
@RequestMapping("/remedios") //mapeia o endpoint, url
public class MedicineController {
    
    @PostMapping 
    public void cadastrar(@RequestBody MedicineDTO novoRemedio){//@RequestBody serve para o Spring entender que essa String "sairá" do JSON que foi mandado


        System.out.println(novoRemedio);
    }
}
