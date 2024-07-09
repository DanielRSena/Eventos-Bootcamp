package com.danielrsena.pass_in.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.danielrsena.pass_in.dataTranferObjects.AttendeeBadgeDTO;
import com.danielrsena.pass_in.service.AttendeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "/attendees", produces = {"application/json"})
@Tag(name = "Attendee Controller")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;
    
    @Operation(summary = "Pede o badge do usuário inscrito no evento", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna o badge do evento do attendee"),
        @ApiResponse(responseCode = "404", description = "Não encontra o ID especificado")
    })
    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
        AttendeeBadgeDTO attendeeBadgeDTO = this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);
        return ResponseEntity.ok(attendeeBadgeDTO);
    }

    @Operation(summary = "Faz o checkIn do usuário inscrito no evento", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna o badge do evento do attendee"),
        @ApiResponse(responseCode = "404", description = "Não encontra o ID especificado")
    })
    @PostMapping("/{attendeeId}/check-in")
    @SuppressWarnings("rawtypes")
    public ResponseEntity registerCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        this.attendeeService.checkInAttendee(attendeeId);
        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();
        return ResponseEntity.created(uri).build();
    }
========
package com.danielrsena.pass_in.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.danielrsena.pass_in.dataTranferObjects.AttendeeBadgeDTO;
import com.danielrsena.pass_in.service.AttendeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "/attendees", produces = {"application/json"})
@Tag(name = "Attendee Controller")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;
    
    @Operation(summary = "Pede o badge do usuário inscrito no evento", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna o badge do evento do attendee"),
        @ApiResponse(responseCode = "404", description = "Não encontra o ID especificado")
    })
    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
        AttendeeBadgeDTO attendeeBadgeDTO = this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);
        return ResponseEntity.ok(attendeeBadgeDTO);
    }

    @Operation(summary = "Faz o checkIn do usuário inscrito no evento", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna o badge do evento do attendee"),
        @ApiResponse(responseCode = "404", description = "Não encontra o ID especificado")
    })
    @PostMapping("/{attendeeId}/check-in")
    @SuppressWarnings("rawtypes")
    public ResponseEntity registerCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        this.attendeeService.checkInAttendee(attendeeId);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();
        return ResponseEntity.created(uri).build();
    }
>>>>>>>> 48fe805617d24905df2467dca22604af2f9e4289:passIn/src/main/java/com/danielrsena/pass_in/controllers/AttendeeController.java
}