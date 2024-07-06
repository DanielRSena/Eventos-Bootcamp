package com.danielrsena.certification_nlw.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielrsena.certification_nlw.dtos.VerifyIfHasCertificationDto;
import com.danielrsena.certification_nlw.repositories.CertificationStudentRepository;

@Service //camada de serviço
public class VerifyIfHasCertificationUseCase{

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    
    public boolean execute(VerifyIfHasCertificationDto dto) {
        var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
        if (!result.isEmpty()) return true;
        else return false;
    }
}