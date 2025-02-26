package com.movieflix.dto;

import lombok.Builder;

@Builder
public record StreamingRequestDTO (Long id, String name) {
}
