package com.danielrsena.pass_in.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielrsena.pass_in.entities.CheckIn;

public interface CheckInRepository extends JpaRepository <CheckIn, Integer> {
    
}
