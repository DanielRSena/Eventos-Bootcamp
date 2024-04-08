package com.danielrsena.pass_in.dataTranferObjects;

import java.time.LocalDateTime;
public record AttendeeDetails(
    String id, 
    String nome, 
    String email, 
    LocalDateTime createdAt, 
    LocalDateTime checkInAt) {
}