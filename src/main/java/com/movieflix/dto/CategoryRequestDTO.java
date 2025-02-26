package com.movieflix.dto;

import lombok.Builder;

@Builder
public record CategoryRequestDTO (Long id, String name){
}
