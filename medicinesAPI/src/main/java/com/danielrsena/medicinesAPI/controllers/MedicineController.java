package com.danielrsena.medicinesAPI.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrsena.medicinesAPI.dto.ListOfMedicines;
import com.danielrsena.medicinesAPI.dto.MedicineDTO;
import com.danielrsena.medicinesAPI.dto.UpdateMedicine;
import com.danielrsena.medicinesAPI.entities.Medicine;
import com.danielrsena.medicinesAPI.repository.MedicineRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController //indica ao String que deve iniciá-la como um controller
@RequestMapping("/remedios") //mapeia o endpoint, url
@Transactional //faz um rowback, a transação é revertida
public class MedicineController {

    @Autowired private MedicineRepository medRepository;
    
    @PostMapping 
    public void cadastrar(@RequestBody @Valid MedicineDTO newMedicine){//@RequestBody serve para o Spring entender que essa String "sairá" do JSON que foi mandado

    medRepository.save(new Medicine(newMedicine));
    }

    @GetMapping
    public List<ListOfMedicines> lerMedicines(){
        
        return medRepository.findAll().stream().map(ListOfMedicines::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarMedicine(@RequestBody @Valid UpdateMedicine updatedMedicine){

        var medicine = medRepository.getReferenceById(updatedMedicine.id());
        medicine.updateMedicine(updatedMedicine);
    }
}