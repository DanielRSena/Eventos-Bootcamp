package com.danielrsena.planner.repositories;

import com.danielrsena.planner.entities.Activity;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
        List<Activity> findByTripId(UUID tripId); // Não é necessário montar uma query para isso, pois o JPA já faz isso por meio da chave estrangeira, ele sabe como a query é feita.
}