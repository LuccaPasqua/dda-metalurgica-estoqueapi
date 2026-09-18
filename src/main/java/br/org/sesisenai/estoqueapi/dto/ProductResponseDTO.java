package br.org.sesisenai.estoqueapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        String code,
        String unitOfmeasurement,
        BigDecimal unitCost,
        BigDecimal MinStockLevel,
        String manufacturerName,
        LocalDateTime createdAt
) { }
