package com.movieflix.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequestDTO (String title,
                               String description,
                               Double rating,
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                               LocalDate releaseDate,
                               List<Long> categories,
                               List<Long> streamings) {
}
