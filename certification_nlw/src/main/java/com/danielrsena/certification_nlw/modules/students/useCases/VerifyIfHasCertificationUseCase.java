package com.danielrsena.certification_nlw.modules.students.useCases;

import org.springframework.stereotype.Service;

import com.danielrsena.certification_nlw.modules.students.dto.VerifyIfHasCertificationDto;

@Service //camada de serviço
public class VerifyIfHasCertificationUseCase {

    public boolean execute(VerifyIfHasCertificationDto dto) {
        if (dto.getEmail().equals("danteste@gmail.com") && dto.getTechnology().equals("Java")) {
            return true;
        }
        else return false;

    }
}