package com.danielrsena.pass_in.dataTranferObjects;

public record EventDetailsDTO(
    String id, 
    String title, 
    String detail, 
    String slug,
    Integer maxAttendee,
    Integer attendees) {
}