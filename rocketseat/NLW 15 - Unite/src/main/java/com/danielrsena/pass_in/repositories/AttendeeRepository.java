package com.danielrsena.pass_in.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielrsena.pass_in.entities.Attendee;
import java.util.List;
import java.util.Optional;


public interface AttendeeRepository extends JpaRepository <Attendee, String> {

    List<Attendee> findByEventId(String eventId); //JPA gera método, então nem esquenta

    Optional<Attendee> findByEventIdAndEmail(String eventId, String email);
}