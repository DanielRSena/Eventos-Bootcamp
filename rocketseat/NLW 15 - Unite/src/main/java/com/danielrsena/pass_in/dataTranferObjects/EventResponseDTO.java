package com.danielrsena.pass_in.dataTranferObjects;

import com.danielrsena.pass_in.entities.Event;

import lombok.Getter;

@Getter
public class EventResponseDTO {
    EventDetailsDTO event;

    public EventResponseDTO(Event event, Integer nAttendees) {
        this.event = new EventDetailsDTO(
            event.getId(),
            event.getTitle(),
            event.getDetails(),
            event.getSlug(),
            event.getMaxAttendees(),
            nAttendees
        );
    }
}