package com.danielrsena.planner.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.danielrsena.planner.dtos.TripCreatorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trips")
public class Trip {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String destination;

    @Column(name = "starts_at", nullable = false)
    private LocalDateTime startsAt;

    @Column(name = "ends_at", nullable = false)
    private LocalDateTime endsAt;

    @Column(nullable = false)
    private boolean isConfirmed;

    @Column(name="owner_name", nullable = false)
    private String ownerName;

    @Column(name="owner_email", nullable = false)
    private String ownerEmail;

    public Trip(TripCreatorDTO newTrip) {
        this.destination = newTrip.destination();
        this.startsAt = LocalDateTime.parse(newTrip.startsAt(), DateTimeFormatter.ISO_DATE_TIME);
        this.endsAt = LocalDateTime.parse(newTrip.endsAt(), DateTimeFormatter.ISO_DATE_TIME);
        this.isConfirmed = false;
        this.ownerName = newTrip.ownerName();
        this.ownerEmail = newTrip.ownerEmail();
    }
}