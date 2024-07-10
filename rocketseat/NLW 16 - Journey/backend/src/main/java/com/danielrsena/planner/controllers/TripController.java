package com.danielrsena.planner.controllers;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielrsena.planner.dtos.*;
import com.danielrsena.planner.entities.Trip;
import com.danielrsena.planner.repositories.TripRepository;
import com.danielrsena.planner.services.*;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private TripRepository tripRepository;
    

    @PostMapping
    public ResponseEntity<TripResultCreatorDTO> createTrip(@RequestBody TripCreatorDTO trip) {
        Trip newTrip = new Trip(trip);
        this.tripRepository.save(newTrip);
        this.participantService.registerParticipants(trip.participants(), newTrip);
        return ResponseEntity.ok(new TripResultCreatorDTO(newTrip.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID id) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID id, @RequestBody TripCreatorDTO trip) {
        Optional<Trip> optionalTrip = this.tripRepository.findById(id);

        if (optionalTrip.isPresent()) {
            Trip updatedTrip = optionalTrip.get();
            updatedTrip.setDestination(trip.destination());
            updatedTrip.setStartsAt(LocalDateTime.parse(trip.startsAt(), DateTimeFormatter.ISO_DATE_TIME));
            updatedTrip.setEndsAt(LocalDateTime.parse(trip.endsAt(), DateTimeFormatter.ISO_DATE_TIME));

            this.tripRepository.save(updatedTrip);

            return ResponseEntity.ok(updatedTrip);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/confirm")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID id) {
        Optional<Trip> optionalTrip = this.tripRepository.findById(id);
        
        if (optionalTrip.isPresent()) {
            Trip trip = optionalTrip.get();

            trip.setConfirmed(true);
            this.tripRepository.save(trip);

            this.participantService.triggerConfirmationEmailToParticipants(trip.getId());
            
            return ResponseEntity.ok(trip);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/invite")
    public ResponseEntity<ParticipantInviteDTO> inviteParticipant(@PathVariable UUID id, @RequestBody ParticipantConfirmationDTO participants) {
        Optional<Trip> optionalTrip = this.tripRepository.findById(id);
        if (optionalTrip.isPresent()) {

            Trip trip = optionalTrip.get();

            List<String> emails = new ArrayList<>();
            emails.add(participants.email());

            this.participantService.registerParticipants(emails, trip);

            ParticipantInviteDTO dto = this.participantService.registerParticipant(participants.email(), trip);

            if(trip.isConfirmed())
                this.participantService.triggerConfirmationEmailToParticipant(participants.email());

                return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantDataDTO>> getAllParticipants(@PathVariable UUID id) {
        Optional<Trip> optionalTrip = this.tripRepository.findById(id);
        if (optionalTrip.isPresent()) {
            List<ParticipantDataDTO> participants = this.participantService.getAllParticipantsFromTripId(id);
            return ResponseEntity.ok(participants);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/activities")
    public ResponseEntity<ActivityResponseDTO> registerActivity(@PathVariable UUID id, @RequestBody ActivityCreatorDTO newActivity) {
        Optional<Trip> optionalTrip = this.tripRepository.findById(id);
        if (optionalTrip.isPresent()) {

            Trip trip = optionalTrip.get();
            
            ActivityResponseDTO activityResponseDTO = this.activityService.registerActivity(newActivity, trip);

            return ResponseEntity.ok(activityResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivitiesDataDTO>> getAllActivities(@PathVariable UUID id) {
        Optional<Trip> optionalTrip = this.tripRepository.findById(id);
        if (optionalTrip.isPresent()) {
            List<ActivitiesDataDTO> activities = this.activityService.getAllActivitiesFromTripId(id);
            return ResponseEntity.ok(activities);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/links")
    public ResponseEntity<LinkResponseDTO> registerLink(@PathVariable UUID id, @RequestBody LinkCreatorDTO linkCreator) {
        Optional<Trip> optionalTrip = this.tripRepository.findById(id);
        if (optionalTrip.isPresent()) {

            Trip trip = optionalTrip.get();
            
            LinkResponseDTO linkResponseDTO = this.linkService.registerLink(linkCreator, trip);

            return ResponseEntity.ok(linkResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/links")
    public ResponseEntity<List<LinksDataDTO>> getAllLinks(@PathVariable UUID id) {
        Optional<Trip> optionalTrip = this.tripRepository.findById(id);
        if (optionalTrip.isPresent()) {
            List<LinksDataDTO> links = this.linkService.getAllLinksFromTripId(id);
            return ResponseEntity.ok(links);
        }
        return ResponseEntity.notFound().build();
    }
}