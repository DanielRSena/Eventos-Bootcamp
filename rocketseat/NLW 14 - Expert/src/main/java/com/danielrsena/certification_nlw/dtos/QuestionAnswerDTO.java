package com.danielrsena.certification_nlw.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerDTO {

    private UUID questionId;
    private UUID alternativeId;
    private boolean isCorrect;
}
