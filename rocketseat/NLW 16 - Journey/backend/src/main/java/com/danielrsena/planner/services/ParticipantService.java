package com.danielrsena.planner.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielrsena.planner.dtos.ParticipantDataDTO;
import com.danielrsena.planner.dtos.ParticipantInviteDTO;
import com.danielrsena.planner.entities.Participant;
import com.danielrsena.planner.entities.Trip;
import com.danielrsena.planner.repositories.ParticipantRepository;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository parRepository;

    public void registerParticipants(List<String> participants, Trip trip) { 

        List<Participant> participantsList = participants.stream().map(email -> new Participant(email, trip)).toList();
        this.parRepository.saveAll(participantsList);
        System.out.println(participantsList.get(0).getId());
    }    

    public ParticipantInviteDTO registerParticipant(String email, Trip trip) {

        Participant participant = new Participant(email, trip);
        this.parRepository.save(participant);
        return new ParticipantInviteDTO(participant.getId());
    }

    public void triggerConfirmationEmailToParticipants(UUID tripId) {}

    public void triggerConfirmationEmailToParticipant(String email) {}

    public List<ParticipantDataDTO> getAllParticipantsFromTripId(UUID id) {
        return this.parRepository.findByTripId(id).stream().map(participant -> new ParticipantDataDTO(participant.getId(), participant.getName(), participant.getEmail(), participant.isConfirmed())).toList();
    }
}