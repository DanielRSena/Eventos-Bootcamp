package com.danielrsena.pass_in.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielrsena.pass_in.entities.Attendee;

public interface AttendeeRepository extends JpaRepository <Attendee, String> {
}