package com.danielrsena.planner.dtos;

import java.util.UUID;

public record ParticipantDataDTO (UUID id, String name, String email, boolean isConfirmed){}