package com.danielrsena.certification_nlw.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielrsena.certification_nlw.modules.questions.entities.QuestionEntity;
import com.danielrsena.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.danielrsena.certification_nlw.modules.questions.repositories.StudentRepository;
import com.danielrsena.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.danielrsena.certification_nlw.modules.students.dto.VerifyIfHasCertificationDto;
import com.danielrsena.certification_nlw.modules.students.entities.AnswersCertificationEntity;
import com.danielrsena.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.danielrsena.certification_nlw.modules.students.entities.StudentEntity;
import com.danielrsena.certification_nlw.modules.students.repositories.CertificationStudentRepository;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired private CertificationStudentRepository certificationStudentRepository;

    @Autowired private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;


    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

        var hasCertification = this.verifyIfHasCertificationUseCase
            .execute(new VerifyIfHasCertificationDto(dto.getEmail(), dto.getTechnology()));

        if(hasCertification) throw new Exception("Você já tirou a sua certificação");

        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationEntity> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionsAndAnswers() .stream().forEach(questionAnswer -> {
            var question = questionsEntity.stream()
                .filter(questionFilter -> questionFilter.getId().equals(questionAnswer.getQuestionId()))
                    .findFirst().get();

            var correctAlternative = question.getAlternatives().stream().
                filter(alternative -> alternative.isCorrect()).findFirst().get();

            if (correctAlternative.getId().equals(questionAnswer.getAlternativeId())) {
                questionAnswer.setCorrect(true);
                correctAnswers.incrementAndGet();
            }
            else questionAnswer.setCorrect(false);

            var answersCertificationEntity = AnswersCertificationEntity.builder()
            .answerId(questionAnswer.getAlternativeId())
            .questionId(questionAnswer.getQuestionId())
            .isCorrect(questionAnswer.isCorrect()).build();
            answersCertifications.add(answersCertificationEntity);
        });

        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentId;
        if(student.isEmpty()) {
           var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentId = studentCreated.getId();
        }
        else studentId = student.get().getId();

       

        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
            .technology(dto.getTechnology()).studentId(studentId).grate(correctAnswers.get()).build();

            var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

            answersCertifications.stream().forEach(answersCertification -> {
                answersCertification.setCertificationId(certificationStudentEntity.getId());
                answersCertification.setCertificationStudentEntity(certificationStudentEntity);

            });

            certificationStudentEntity.setAnswersCertificationEnties(answersCertifications);

            certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;
    }
}
