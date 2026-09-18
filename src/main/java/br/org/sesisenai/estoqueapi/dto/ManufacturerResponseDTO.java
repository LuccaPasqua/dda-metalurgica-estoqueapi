package br.org.sesisenai.estoqueapi.dto;

import java.time.LocalDateTime;

public record ManufacturerResponseDTO(
    Long id,
    String name,
    String document,
    String contact,
    LocalDateTime createdAt
) { }
