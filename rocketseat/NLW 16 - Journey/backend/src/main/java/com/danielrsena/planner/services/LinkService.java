package com.danielrsena.planner.services;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielrsena.planner.dtos.LinkCreatorDTO;
import com.danielrsena.planner.dtos.LinkResponseDTO;
import com.danielrsena.planner.dtos.LinksDataDTO;
import com.danielrsena.planner.entities.Link;
import com.danielrsena.planner.entities.Trip;
import com.danielrsena.planner.repositories.LinkRepository;

@Service
public class LinkService {
    
    @Autowired
    private LinkRepository linkRepository;

    public LinkResponseDTO registerLink(LinkCreatorDTO linkCreatorDTO, Trip trip) {

        Link newLink = new Link(linkCreatorDTO.title(), linkCreatorDTO.url(), linkCreatorDTO.occursAt(), trip);
        this.linkRepository.save(newLink);
        return new LinkResponseDTO(newLink.getId());
    }

    public List<LinksDataDTO> getAllLinksFromTripId(UUID tripId) {
        return this.linkRepository.findByTripId(tripId).stream().map(link -> new LinksDataDTO(link.getId(), link.getTitle(), link.getUrl())).toList();
    }
}