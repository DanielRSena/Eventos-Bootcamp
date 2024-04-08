package com.danielrsena.pass_in.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.danielrsena.pass_in.entities.Attendee;
import com.danielrsena.pass_in.entities.CheckIn;
import com.danielrsena.pass_in.exception.ConflictException;
import com.danielrsena.pass_in.repositories.CheckInRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckInService {
    
    private final CheckInRepository checkInRepository;

    public void registerCheckIn(Attendee attendee){
        this.verifyIfCheckInExists(attendee.getId());
        CheckIn checkIn = new CheckIn();
        checkIn.setAttendee(attendee);
        checkIn.setCreatedAt(LocalDateTime.now());
        this.checkInRepository.save(checkIn);
    }

    private void verifyIfCheckInExists(String attendeeId) {
        Optional<CheckIn> isCheckIn = this.getCheckIn(attendeeId);
        if(isCheckIn.isPresent()) throw new ConflictException("The attendee with the ID '" + attendeeId + "' has already been registered for the event");
    }

    public Optional<CheckIn> getCheckIn(String attendeeId){
        return this.checkInRepository.findByAttendeeId(attendeeId);
    }
}