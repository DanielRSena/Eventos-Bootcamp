package com.danielrsena.planner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielrsena.planner.entities.Link;

import java.util.Collection;
import java.util.UUID;

public interface LinkRepository extends JpaRepository<Link, UUID> {
    public Collection<Link> findByTripId(UUID tripId);
}