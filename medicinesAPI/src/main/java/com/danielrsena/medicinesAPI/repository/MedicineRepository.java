package com.danielrsena.medicinesAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielrsena.medicinesAPI.entities.Medicine;

//<> são generics, o objeto que será mapeado e o objeto que identifica-o
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}