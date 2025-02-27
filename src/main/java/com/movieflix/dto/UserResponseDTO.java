package com.movieflix.dto;

import lombok.Builder;

@Builder
public record UserResponseDTO (Long id, String email, String username) {
}
