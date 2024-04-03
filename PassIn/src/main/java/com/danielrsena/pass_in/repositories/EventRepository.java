package com.danielrsena.pass_in.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielrsena.pass_in.entities.Event;

public interface EventRepository extends JpaRepository <Event, String> {

    
    
}
