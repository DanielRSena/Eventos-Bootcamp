package com.danielrsena.pass_in.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.danielrsena.pass_in.dataTranferObjects.AttendeeIdDTO;
import com.danielrsena.pass_in.dataTranferObjects.AttendeeRequestDTO;
import com.danielrsena.pass_in.dataTranferObjects.AttendeesListResponseDTO;
import com.danielrsena.pass_in.dataTranferObjects.EventIdDto;
import com.danielrsena.pass_in.dataTranferObjects.EventRequestDTO;
import com.danielrsena.pass_in.dataTranferObjects.EventResponseDTO;
import com.danielrsena.pass_in.service.AttendeeService;
import com.danielrsena.pass_in.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    
    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}") //mesmo nome do PathVariable
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
        EventResponseDTO event = this.eventService.getEventDetails(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDto> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDto event = this.eventService.createEvent(body);
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(event.id()).toUri();
        return ResponseEntity.created(uri).body(event);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerParticipant(@PathVariable String eventId, @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        AttendeeIdDTO attendeeIdDTO = this.eventService.registerAttendeeOnEvent(eventId, body);
        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.AttendeeID()).toUri();
        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }

    @GetMapping("/attendees/{id}") //mesmo nome do PathVariable
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id){
        AttendeesListResponseDTO event = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(event);
    }
}