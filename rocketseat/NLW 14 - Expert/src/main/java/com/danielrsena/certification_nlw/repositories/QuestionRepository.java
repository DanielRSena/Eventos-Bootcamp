package com.danielrsena.certification_nlw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielrsena.certification_nlw.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, String>{
    
    List<QuestionEntity> findByTechnology(String technology);
}
