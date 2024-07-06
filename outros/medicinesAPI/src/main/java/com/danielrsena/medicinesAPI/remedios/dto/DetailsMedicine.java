package com.danielrsena.medicinesAPI.remedios.dto;

import java.time.LocalDate;

import com.danielrsena.medicinesAPI.remedios.entities.Laboratorio;
import com.danielrsena.medicinesAPI.remedios.entities.Medicine;
import com.danielrsena.medicinesAPI.remedios.entities.Via;

public record DetailsMedicine( 
    Long id, 
    String nome, 
    Via via, 
    String lote, 
    int quantidade, 
    LocalDate validade, 
    Laboratorio laboratorio,
    boolean ativo) {

    public DetailsMedicine(Medicine medicine) {

        this(

                medicine.getId(),
                medicine.getNome(),
                medicine.getVia(),
                medicine.getLote(),
                medicine.getQuantidade(),
                medicine.getValidade(),      
                medicine.getLaboratorio(),
                medicine.isAtivo());
    }
}
