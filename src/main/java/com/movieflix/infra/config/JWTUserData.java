package com.movieflix.infra.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long id, String username, String email) {
}
