package com.danielrsena.certification_nlw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrsena.certification_nlw.useCases.Top10RankingUseCase;
import com.danielrsena.certification_nlw.entities.CertificationStudentEntity;

@RestController
@RequestMapping("/ranking")
public class RankingController{

    @Autowired private Top10RankingUseCase top10RankingUseCase;

    @GetMapping("/top10")
    public List<CertificationStudentEntity> top10(){
        return this.top10RankingUseCase.execute();
    }
}