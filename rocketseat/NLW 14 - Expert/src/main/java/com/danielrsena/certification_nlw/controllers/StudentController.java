package com.danielrsena.certification_nlw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.danielrsena.certification_nlw.repositories.StudentRepository;
import com.danielrsena.certification_nlw.dtos.CreateStudent;
import com.danielrsena.certification_nlw.dtos.StudentCertificationAnswerDTO;
import com.danielrsena.certification_nlw.dtos.VerifyIfHasCertificationDto;
import com.danielrsena.certification_nlw.entities.StudentEntity;
import com.danielrsena.certification_nlw.useCases.StudentCertificationAnswersUseCase;
import com.danielrsena.certification_nlw.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired StudentRepository studentRepository;
    @Autowired private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;
    @Autowired private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping() //cria os estudantes
    public ResponseEntity<StudentEntity> createStudent(@RequestBody CreateStudent newStudent, UriComponentsBuilder uriComponentsBuilder){
        StudentEntity studentEntity = new StudentEntity(newStudent);
        this.studentRepository.save(studentEntity);
        var uri = uriComponentsBuilder.path("/students/{id}").buildAndExpand(studentEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(studentEntity);
    }

    @DeleteMapping("/{id}") //deleta os estudantes
    public ResponseEntity<?> deleteStudent(@RequestBody @PathVariable String id){
        this.studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping //busca os estudantes
    public ResponseEntity <Object> getStudents(){
        var result = this.studentRepository.findAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Object> getStudent(@PathVariable String id){
        var result = this.studentRepository.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyIfHasCertificationDto verifyIfHasCertificationDto) {
        var result = this.verifyIfHasCertificationUseCase.execute(verifyIfHasCertificationDto);
        if (result) return "Já tem essa certificação, não pode fazer a prova.";
        else return "Foi, user pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity <Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {

            try {
                var result = studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
                return ResponseEntity.ok().body(result);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }        
    }
}