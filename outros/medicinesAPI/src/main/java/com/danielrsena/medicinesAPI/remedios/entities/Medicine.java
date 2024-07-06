package com.danielrsena.medicinesAPI.remedios.entities;

import java.time.LocalDate;

import com.danielrsena.medicinesAPI.remedios.dto.MedicineDTO;
import com.danielrsena.medicinesAPI.remedios.dto.UpdateMedicine;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Medicine") //demonstra que a classe é uma entidade do BD
@EqualsAndHashCode(of = "id")
@Table(name = "Medicines") //representada numa tabela do BD com nome Remedios
public class Medicine {

    private String lote; 
    private String nome;
    private boolean ativo;
    private int quantidade; 
    private LocalDate validade; 
    @Enumerated(EnumType.STRING) private Via via; 
    @Enumerated(EnumType.STRING) private Laboratorio laboratorio;
    //diz que esse atributo é enum e deve ser enumerado pelo JPA
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; 
    //id, e indica que será uma primary key. Gera em ordem

    public Medicine(MedicineDTO dados){

        this.nome = dados.nome();
        this.via = dados.via();
        this.lote = dados.lote();
        this.quantidade = dados.quantidade();
        this.validade = dados.validade();
        this.laboratorio = dados.laboratorio();
        this.ativo = true;
    }

    public void updateMedicine(@Valid UpdateMedicine updatedMedicine) {

        if(updatedMedicine.nome() != null) this.nome = updatedMedicine.nome();
        if(updatedMedicine.via() != null) this.via = updatedMedicine.via();
        if(updatedMedicine.laboratorio() != null) this.laboratorio = updatedMedicine.laboratorio();
    }

    public void inativar() { this.ativo = false; }

    public void reativar() { this.ativo = true; }
}