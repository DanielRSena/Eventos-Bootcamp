package com.danielrsena.medicinesAPI.remedios.dto;

import java.time.LocalDate;

import com.danielrsena.medicinesAPI.remedios.entities.Laboratorio;
import com.danielrsena.medicinesAPI.remedios.entities.Via;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record MedicineDTO( //mais fácil que a class, menos burocracia com setters e getters por exemplo

    @NotBlank String nome, 
    @Enumerated Via via, 
    @NotBlank String lote, 
    @NonNull int quantidade, 
    @Future LocalDate validade, //não podemos aceitar remédios vencidos, não é verdade?
    @Enumerated Laboratorio laboratorio
    ){}