package com.danielrsena.planner.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielrsena.planner.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, UUID> {}