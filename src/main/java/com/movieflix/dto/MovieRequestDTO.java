package com.movieflix.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequestDTO (@NotBlank(message = "Title is required") String title,
                               String description,
                               Double rating,
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                               LocalDate releaseDate,
                               List<Long> categories,
                               List<Long> streamings) {
}
