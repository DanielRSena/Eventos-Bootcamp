package com.danielrsena.medicinesAPI.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Remedio") //demonstra que a classe é uma entidade do BD
@EqualsAndHashCode(of = "id")
@Table(name = "Remedios") //representada numa tabela do BD com nome Remedios
public class Remedio {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; //id, e indica que será uma primary key. Gera em ordem
    private String nome;
    @Enumerated(EnumType.STRING) private Via via; 
    //diz que esse atributo é enum e deve ser enumerado pelo JPA
    private String lote; 
    private String quantidade; 
    private String validade; 
    @Enumerated(EnumType.STRING) private Laboratorio lab;
}