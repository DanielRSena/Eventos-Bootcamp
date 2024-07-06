package com.danielrsena.certification_nlw.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielrsena.certification_nlw.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, String>{
    
    public Optional<StudentEntity> findByEmail(String email);
}
