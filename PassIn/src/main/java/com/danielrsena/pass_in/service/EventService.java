package com.danielrsena.pass_in.service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.danielrsena.pass_in.dataTranferObjects.AttendeeIdDTO;
import com.danielrsena.pass_in.dataTranferObjects.AttendeeRequestDTO;
import com.danielrsena.pass_in.dataTranferObjects.EventIdDto;
import com.danielrsena.pass_in.dataTranferObjects.EventRequestDTO;
import com.danielrsena.pass_in.dataTranferObjects.EventResponseDTO;
import com.danielrsena.pass_in.entities.Attendee;
import com.danielrsena.pass_in.entities.Event;
import com.danielrsena.pass_in.exception.EventFullException;
import com.danielrsena.pass_in.exception.EventNotFoundException;
import com.danielrsena.pass_in.repositories.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetails(String eventId){
        Event event = this.getEventById(eventId);
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDto createEvent(EventRequestDTO dto){
        Event newEvent = new Event();
        newEvent.setTitle(dto.title());
        newEvent.setDetails(dto.details());
        newEvent.setMaxAttendees(dto.maxAttendees());
        newEvent.setSlug(this.createSlug(dto.title()));

        this.eventRepository.save(newEvent);
        return new EventIdDto(newEvent.getId());
    }

    public AttendeeIdDTO registerAttendeeOnEvent(String eventId, AttendeeRequestDTO attendeeRequestDTO){
        this.attendeeService.verifyAttendeeSubscription(attendeeRequestDTO.email(), eventId);
        Event event = this.getEventById(eventId);
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);

        if (event.getMaxAttendees() <= attendeeList.size())  throw new EventFullException("this event is full");
        Attendee newAttendee = new Attendee();
        newAttendee.setName(attendeeRequestDTO.name());
        newAttendee.setEmail(attendeeRequestDTO.email());
        newAttendee.setEvent(event);
        newAttendee.setCreatedAt(LocalDateTime.now());
        this.attendeeService.criaAttendee(newAttendee);

        return new AttendeeIdDTO(newAttendee.getId());
    }

    private Event getEventById(String eventId){

        return this.eventRepository.findById(eventId)
        .orElseThrow(() -> new EventNotFoundException("Event not founded with ID "+ eventId));
    }

    private String createSlug(String title){
        String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
        return slug.replaceAll("\\p{M}", "")
        .replaceAll("[^\\w\\s]", "")
        .replaceAll("[\\s+]", "-")
        .toLowerCase();
    }
}