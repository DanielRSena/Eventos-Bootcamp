package com.danielrsena.planner.dtos;

import java.util.List;

public record TripCreatorDTO (String destination, String startsAt, String endsAt, List<String> participants, String ownerName, String ownerEmail) {}