package com.danielrsena.medicinesAPI.infraestrutura;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.danielrsena.medicinesAPI.remedios.dto.DetailsMedicine;
import com.danielrsena.medicinesAPI.remedios.dto.ListOfMedicines;
import com.danielrsena.medicinesAPI.remedios.dto.MedicineDTO;
import com.danielrsena.medicinesAPI.remedios.dto.UpdateMedicine;
import com.danielrsena.medicinesAPI.remedios.entities.Medicine;
import com.danielrsena.medicinesAPI.remedios.repository.MedicineRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController //indica ao String que deve iniciá-la como um controller
@RequestMapping("/remedios") //mapeia o endpoint, url
@Transactional //faz um rowback, a transação é revertida
public class MedicineController {

    @Autowired private MedicineRepository medRepository;
    
    @PostMapping 
    public ResponseEntity<DetailsMedicine> cadastrar(@RequestBody @Valid MedicineDTO newMedicine, UriComponentsBuilder url){//@RequestBody serve para o Spring entender que essa String "sairá" do JSON que foi mandado

        var remedio = new Medicine(newMedicine);
        medRepository.save(remedio);

        var uri = url.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

        var dto = new DetailsMedicine(remedio);

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ListOfMedicines>> lerMedicines(){
       var lista = medRepository.findAllByAtivoTrue().stream().map(ListOfMedicines::new).toList(); 
       return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetailsMedicine> atualizarMedicine(@RequestBody @Valid UpdateMedicine updatedMedicine){

        var medicine = medRepository.getReferenceById(updatedMedicine.id());
        medicine.updateMedicine(updatedMedicine);

        return ResponseEntity.ok(new DetailsMedicine(medicine));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteMapping(@PathVariable @NotNull Long id){ //serve para o spring enter que é o var que usarás no deletemapping

        medRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Void> desactiveMapping(@PathVariable Long id){

        var medicine = medRepository.getReferenceById(id);
        medicine.inativar();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativaMedicine(@PathVariable Long id) {
        
        var medicine = medRepository.getReferenceById(id);
        medicine.reativar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsMedicine> findMedicineById(@PathVariable Long id){

        var remedio = medRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetailsMedicine(remedio));
    }

}