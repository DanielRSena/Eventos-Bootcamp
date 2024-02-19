package com.danielrsena.medicinesAPI.dto;

import java.time.LocalDate;

import com.danielrsena.medicinesAPI.entities.Laboratorio;
import com.danielrsena.medicinesAPI.entities.Medicine;
import com.danielrsena.medicinesAPI.entities.Via;

public record ListOfMedicines(Long id, String nome, Via via, String lote, Laboratorio laboratorio, LocalDate validade) {

    public ListOfMedicines(Medicine medicine){

        this(
            
            medicine.getId(), 
            medicine.getNome(), 
            medicine.getVia(), 
            medicine.getLote(), 
            medicine.getLaboratorio(), 
            medicine.getValidade()
        );
    }
}
