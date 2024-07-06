package com.danielrsena.certification_nlw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.danielrsena.certification_nlw.entities.CertificationStudentEntity;

@Repository //não necessariamente, ele sabe que é pois extende
public interface CertificationStudentRepository extends JpaRepository<CertificationStudentEntity, String>{
    
    @Query("SELECT c FROM certifications c INNER JOIN c.studentEntity std WHERE std.email = :email AND c.technology = :technology")
    List<CertificationStudentEntity> findByStudentEmailAndTechnology(String email, String technology);

    @Query("SELECT c FROM certifications c ORDER by c.grate DESC LIMIT 10")
    List<CertificationStudentEntity> findTop10ByOrderByGrateDesc();
}