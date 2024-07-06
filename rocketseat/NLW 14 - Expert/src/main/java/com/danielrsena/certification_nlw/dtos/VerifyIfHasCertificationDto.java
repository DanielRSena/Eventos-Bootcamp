package com.danielrsena.certification_nlw.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyIfHasCertificationDto {
    
    private String email;
    private String technology;
}
