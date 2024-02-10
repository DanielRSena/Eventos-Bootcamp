package com.danielrsena.certification_nlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrsena.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.danielrsena.certification_nlw.modules.students.dto.VerifyIfHasCertificationDto;
import com.danielrsena.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.danielrsena.certification_nlw.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.danielrsena.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyIfHasCertificationDto verifyIfHasCertificationDto) {

        var result = this.verifyIfHasCertificationUseCase.execute(verifyIfHasCertificationDto);
        if (result) return "Já tem essa certificação, não pode fazer a prova.";
        else return "Foi, user pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity <Object> certificationAnswer(
            @RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {

            try {
                var result = studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
                return ResponseEntity.ok().body(result);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        
    }

}
