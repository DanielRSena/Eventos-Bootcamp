package com.danielrsena.medicinesAPI.dto;

import com.danielrsena.medicinesAPI.entities.Laboratorio;
import com.danielrsena.medicinesAPI.entities.Via;

import jakarta.validation.constraints.NotNull;

public record UpdateMedicine(

    @NotNull Long id, 
    String nome, 
    Via via, 
    Laboratorio laboratorio) {
}
