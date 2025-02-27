package com.movieflix.mapper;

import com.movieflix.dto.CategoryResponseDTO;
import com.movieflix.dto.MovieRequestDTO;
import com.movieflix.dto.MovieResponseDTO;
import com.movieflix.dto.StreamingResponseDTO;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public Movie map(MovieRequestDTO requestDTO){

        List<Category> categories = requestDTO.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = requestDTO.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie
                .builder()
                .title(requestDTO.title())
                .description(requestDTO.description())
                .rating(requestDTO.rating())
                .releaseDate(requestDTO.releaseDate())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public MovieResponseDTO map(Movie movie){

        List<CategoryResponseDTO> categories = movie.getCategories().stream()
                .map(CategoryMapper::map)
                .toList();

        List<StreamingResponseDTO> streamings = movie.getStreamings().stream()
                .map(StreamingMapper::map)
                .toList();

        return MovieResponseDTO
                .builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .categories(categories)
                .streamings(streamings)
                .build();

    }
}
