package br.org.sesisenai.estoqueapi.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String description,
        String code,
        String unitOfMeasurement,
        BigDecimal unitCost,
        BigDecimal minStockLevel,
        Long manufacturerId
) { }
