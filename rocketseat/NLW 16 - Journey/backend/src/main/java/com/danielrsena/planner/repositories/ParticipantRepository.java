package com.danielrsena.planner.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielrsena.planner.entities.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    List<Participant> findByTripId(UUID tripId); // Não é necessário montar uma query para isso, pois o JPA já faz isso através da chave estrangeira, ele sabe como a query é feita.
}