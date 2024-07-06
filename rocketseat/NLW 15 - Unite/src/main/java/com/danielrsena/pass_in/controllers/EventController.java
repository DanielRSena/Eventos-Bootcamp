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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@Tag(name = "Event Controller")
@RequiredArgsConstructor
public class EventController {
    
    private final EventService eventService;
    private final AttendeeService attendeeService;

    @Operation(summary = "Obtem os detalhes de um evento", method = "GET")
    @ApiResponses(value = {  
        @ApiResponse(responseCode = "200", description = "Lista os detalhes do evento"),
        @ApiResponse(responseCode = "404", description = "O ID do evento não foi encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
        EventResponseDTO event = this.eventService.getEventDetails(id);
        return ResponseEntity.ok(event);
    }

    @Operation(summary = "Cria um novo evento", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cria o evento e retorna seu EventDTO"),
        @ApiResponse(responseCode = "400", description = "Já existe um evento com o mesmo título")
    })
    @PostMapping
    public ResponseEntity<EventIdDto> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDto event = this.eventService.createEvent(body);
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(event.id()).toUri();
        return ResponseEntity.created(uri).body(event);
    }

    @Operation(summary = "Registra participantes em um evento", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Registra o participante no evento e retorna seu id"),
        @ApiResponse(responseCode = "400", description = "Não realiza a ação, pois o attendee já está registrado"),
        @ApiResponse(responseCode = "404", description = "O ID do evento não foi encontrado")
    })
    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerParticipant(@PathVariable String eventId, @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        AttendeeIdDTO attendeeIdDTO = this.eventService.registerAttendeeOnEvent(eventId, body);
        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.AttendeeID()).toUri();
        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }

    @Operation(summary = "Lista os participantes de um evento", method = "GET")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Lista os participantes do evento"),
        @ApiResponse(responseCode = "404", description = "O ID do evento não foi encontrado")
    })
    @GetMapping("/attendees/{id}") //mesmo nome do PathVariable
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id){
        AttendeesListResponseDTO event = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(event);
    }
}