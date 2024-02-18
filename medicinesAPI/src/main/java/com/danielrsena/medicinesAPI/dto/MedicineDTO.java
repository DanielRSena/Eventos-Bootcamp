package com.danielrsena.medicinesAPI.dto;

import com.danielrsena.medicinesAPI.entities.Laboratorio;
import com.danielrsena.medicinesAPI.entities.Via;

public record MedicineDTO( //mais fácil que a class, menos burocracia com setters e getters por exemplo

    String nome, 
    Via via, 
    String lote, 
    String quantidade, 
    String validade, 
    Laboratorio lab ) {    
}
