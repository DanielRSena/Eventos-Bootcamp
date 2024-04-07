package com.danielrsena.pass_in.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.danielrsena.pass_in.dataTranferObjects.AttendeeBadgeDTO;
import com.danielrsena.pass_in.dataTranferObjects.AttendeeDetails;
import com.danielrsena.pass_in.dataTranferObjects.AttendeesListResponseDTO;
import com.danielrsena.pass_in.dataTranferObjects.BadgeDTO;
import com.danielrsena.pass_in.entities.Attendee;
import com.danielrsena.pass_in.entities.CheckIn;
import com.danielrsena.pass_in.exception.AttendeeAlreadyRegisteredException;
import com.danielrsena.pass_in.repositories.AttendeeRepository;
import com.danielrsena.pass_in.exception.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    
    private final AttendeeRepository attendeeRepository;
    private final CheckInService checkInService;

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkInService.getCheckIn(attendee.getId());
            LocalDateTime checkedAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }

    public void verifyAttendeeSubscription(String email, String eventId){
        Optional<Attendee> isRegistered = this.attendeeRepository.findByEventIdAndEmail(eventId, email);
        if(isRegistered.isPresent()) throw new AttendeeAlreadyRegisteredException("Attendee is already registered");

    }

    //@PostMapping
    public Attendee criaAttendee(Attendee newAttendee){

        this.attendeeRepository.save(newAttendee);
        return newAttendee;
    }

    public AttendeeBadgeDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder){
        Attendee attendee = this.getAttendee(attendeeId);
        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/check-in").buildAndExpand(attendeeId).toUri().toString();
        BadgeDTO badge = new BadgeDTO(attendee.getName(), attendee.getEmail(), uri, attendee.getEvent().getId());
        return new AttendeeBadgeDTO(badge);
    }

    public void checkInAttendee(String attendeeId) {
        
        Attendee attendee = getAttendee(attendeeId);
        this.checkInService.registerCheckIn(attendee);
    }    

    private Attendee getAttendee(String attendeeId){
        Attendee attendee = this.attendeeRepository.findById(attendeeId).orElseThrow(() -> new AttendeeNotFoundException("Attendee not found with id" + attendeeId));
        return attendee;
    }
}