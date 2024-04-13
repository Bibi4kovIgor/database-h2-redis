package edu.lemon.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductsDto(UUID id, String name, String description, BigDecimal price, int quantity) {}
