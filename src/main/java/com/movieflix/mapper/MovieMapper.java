package com.movieflix.mapper;

import com.movieflix.dto.MovieRequestDTO;
import com.movieflix.dto.MovieResponseDTO;
import com.movieflix.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie map(MovieRequestDTO RequestDTO){
        return Movie
                .builder()
                .id(RequestDTO.id())
                .title(RequestDTO.title())
                .description(RequestDTO.description())
                .rating(RequestDTO.rating())
                .releaseDate(RequestDTO.releaseDate())
                .createdAt(RequestDTO.createdAt())
                .updatedAt(RequestDTO.updatedAt())
                .build();
    }

    public MovieResponseDTO map(Movie movie){
        return MovieResponseDTO
                .builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .createdAt(movie.getCreatedAt())
                .updatedAt(movie.getUpdatedAt())
                .build();
    }
}
