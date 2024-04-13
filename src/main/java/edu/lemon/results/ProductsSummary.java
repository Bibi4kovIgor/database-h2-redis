package edu.lemon.results;

import edu.lemon.entities.Categories;
import jakarta.persistence.Column;

public record ProductsSummary(String name, Categories categories) {
}
