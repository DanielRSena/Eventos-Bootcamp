package com.danielrsena.medicinesAPI.remedios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielrsena.medicinesAPI.remedios.entities.Medicine;

//<> são generics, o objeto que será mapeado e o objeto que identifica-o
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findAllByAtivoTrue();

}