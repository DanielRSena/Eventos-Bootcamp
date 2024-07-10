package com.danielrsena.planner.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrsena.planner.dtos.ParticipantConfirmationDTO;
import com.danielrsena.planner.entities.Participant;
import com.danielrsena.planner.repositories.ParticipantRepository;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository parRepository;

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, @RequestBody ParticipantConfirmationDTO dto) {
        Optional<Participant> optionalParticipant = this.parRepository.findById(id);

        if (optionalParticipant.isPresent()) {
            Participant participant = optionalParticipant.get();
            participant.setConfirmed(true);
            participant.setName(dto.name());
            this.parRepository.save(participant);
            return ResponseEntity.ok(participant);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantDetails(@PathVariable UUID id) {
        Optional<Participant> participant = this.parRepository.findById(id);
        return participant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}