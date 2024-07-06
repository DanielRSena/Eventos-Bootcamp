package com.danielrsena.pass_in.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="events")
@Getter
@Setter
@AllArgsConstructor //faz os contrutores que recebem os argumentos
@NoArgsConstructor //gera contrutores que não recebem argumentos
public class Event {
    
    @Id @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false, name = "max_attendees")
    private Integer maxAttendees;
}
