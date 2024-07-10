package com.danielrsena.planner.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivitiesDataDTO (UUID id, String title, LocalDateTime occursAt) {}