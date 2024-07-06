package com.danielrsena.medicinesAPI.remedios.dto;

import com.danielrsena.medicinesAPI.remedios.entities.Laboratorio;
import com.danielrsena.medicinesAPI.remedios.entities.Via;

import jakarta.validation.constraints.NotNull;

public record UpdateMedicine(@NotNull Long id, String nome, Via via, Laboratorio laboratorio) {}