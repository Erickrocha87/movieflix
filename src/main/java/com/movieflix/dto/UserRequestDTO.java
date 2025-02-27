package com.movieflix.dto;

import lombok.Builder;

@Builder
public record UserRequestDTO(String email, String username, String password) {
}
