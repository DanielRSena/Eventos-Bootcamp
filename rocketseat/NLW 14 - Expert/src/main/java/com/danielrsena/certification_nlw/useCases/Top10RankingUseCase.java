package com.danielrsena.certification_nlw.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielrsena.certification_nlw.entities.CertificationStudentEntity;
import com.danielrsena.certification_nlw.repositories.CertificationStudentRepository;

@Service
public class Top10RankingUseCase {

    @Autowired private CertificationStudentRepository certificationStudentRepository;
    
    public List<CertificationStudentEntity> execute(){
        var result = this.certificationStudentRepository.findTop10ByOrderByGrateDesc();
        return result;
    }
}