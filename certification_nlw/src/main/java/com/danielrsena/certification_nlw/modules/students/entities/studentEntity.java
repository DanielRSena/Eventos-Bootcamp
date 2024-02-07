package com.danielrsena.certification_nlw.modules.students.entities;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //colocar getters e setters automaticamente, serve @Getter e @stter também
@AllArgsConstructor
@NoArgsConstructor
public class studentEntity {
    
    private UUID id; //esse UUID é um identidicador muito usado
    private String email;
    private List<CertificationStudentEntity> certificationStudentEntity;

}
