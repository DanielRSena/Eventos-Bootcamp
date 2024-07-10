package com.danielrsena.planner.dtos;

import java.util.UUID;

public record LinksDataDTO (UUID linkId, String title, String url) {}