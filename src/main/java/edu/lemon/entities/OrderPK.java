package edu.lemon.entities;

import java.io.Serializable;
import java.util.UUID;

public record OrderPK(UUID clientId, UUID productId) implements Serializable {}
