package com.movieflix.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record MovieResponseDTO(String title, String description, Integer rating, LocalDate releaseDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
