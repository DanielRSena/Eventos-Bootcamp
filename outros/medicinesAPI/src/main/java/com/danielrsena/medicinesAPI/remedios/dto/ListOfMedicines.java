package com.danielrsena.medicinesAPI.remedios.dto;

import java.time.LocalDate;

import com.danielrsena.medicinesAPI.remedios.entities.Laboratorio;
import com.danielrsena.medicinesAPI.remedios.entities.Medicine;
import com.danielrsena.medicinesAPI.remedios.entities.Via;

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
