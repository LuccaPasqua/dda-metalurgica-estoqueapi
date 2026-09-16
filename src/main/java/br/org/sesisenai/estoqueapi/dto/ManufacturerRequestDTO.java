package br.org.sesisenai.estoqueapi.dto;

public record ManufacturerRequestDTO(
    String name,
    String document,
    String contact
) { }
