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
import com.danielrsena.pass_in.entities.Event;
import com.danielrsena.pass_in.repositories.AttendeeRepository;
import com.danielrsena.pass_in.repositories.EventRepository;
import com.danielrsena.pass_in.exception.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    
    private final AttendeeRepository attendeeRepository;
    private final CheckInService checkInService;
    private final EventRepository eventRepository;

    public Attendee createAttendee(Attendee newAttendee){
        this.attendeeRepository.save(newAttendee);
        return newAttendee;
    }

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
        getEvent(eventId);
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId){
        
        getEvent(eventId);
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);
        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkInService.getCheckIn(attendee.getId());
            LocalDateTime checkedAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }

    public void verifyAttendeeSubscription(String email, String eventId){
        getEvent(eventId);
        Optional<Attendee> isRegistered = this.attendeeRepository.findByEventIdAndEmail(eventId, email);
        if(isRegistered.isPresent()) throw new ConflictException("Attendee with the email '" + email + "' has already been registered");
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
        Attendee attendee = this.attendeeRepository.findById(attendeeId).orElseThrow(() -> new NotFoundException("Attendee with ID '" + attendeeId + "' was not found"));
        return attendee;
    }

    private void getEvent(String eventId) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if(!event.isPresent()) throw new NotFoundException("Event with the ID '" + eventId + "' was not found");
    }
}