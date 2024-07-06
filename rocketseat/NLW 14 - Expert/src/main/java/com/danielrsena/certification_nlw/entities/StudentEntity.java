package com.danielrsena.certification_nlw.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.danielrsena.certification_nlw.dtos.CreateStudent;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //colocar getters e setters automaticamente, serve @Getter e @stter também
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="students")
public class StudentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) private String id;    
    
    @Column(name = "name", nullable = false) private String name;
    
    @Column(name = "email", unique = true, nullable = false) private String email;
    
    @OneToMany(mappedBy="studentEntity") 
    @JsonBackReference
    private List<CertificationStudentEntity> certificationStudentEntity;

    @CreationTimestamp private LocalDateTime createdAt;

    public StudentEntity(CreateStudent createStudent){
        this.name = createStudent.name();
        this.email = createStudent.email();
        this.createdAt = LocalDateTime.now();
    }
}