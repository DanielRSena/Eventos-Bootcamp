package com.danielrsena.certification_nlw.modules.questions.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrsena.certification_nlw.modules.questions.dto.AlternativesresultDTO;
import com.danielrsena.certification_nlw.modules.questions.dto.QuestionResultDTO;
import com.danielrsena.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.danielrsena.certification_nlw.modules.questions.entities.QuestionEntity;
import com.danielrsena.certification_nlw.modules.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
    
    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology){
        System.out.println("TECH == " + technology);

        var result = this.questionRepository.findByTechnology(technology);

        var toMap = result.stream().map(question -> mapQuestionToDTO(question)).collect(Collectors.toList());
        return toMap;
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question){

        var questionResultDTO = QuestionResultDTO .builder().id(question.getId()).technology(question.getTechnology())
        .description(question.getDescription()).build();

        List<AlternativesresultDTO> alternativesresultDTOs = question.getAlternatives()
        .stream().map(alternative -> mapAlternativeDTO(alternative))
        .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesresultDTOs);
        return questionResultDTO;
    }

    static AlternativesresultDTO mapAlternativeDTO(AlternativesEntity alternativesEntity){

        return AlternativesresultDTO.builder().id(alternativesEntity.getId())
        .description(alternativesEntity.getDescription()).build();
    }
}
