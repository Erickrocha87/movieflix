package com.movieflix.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequestDTO (
        @NotBlank(message = "Name is required") String name){
}
