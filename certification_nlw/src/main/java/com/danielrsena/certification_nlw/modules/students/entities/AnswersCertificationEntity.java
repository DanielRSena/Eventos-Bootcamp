package com.danielrsena.certification_nlw.modules.students.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //colocar getters e setters automaticamente, serve @Getter e @stter também
@AllArgsConstructor
@NoArgsConstructor
public class AnswersCertificationEntity {

    private UUID id;
    private UUID certificationId;
    private UUID studentId;
    private UUID questionId;
    private UUID answerId;
    private boolean isCorrect;
}
