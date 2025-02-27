package com.movieflix.services;

import com.movieflix.dto.MovieResponseDTO;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final CategoryService categoryService;

    private final StreamingService streamingService;

    private final MovieRepository movieRepository;

    public List<MovieResponseDTO> findAll(){
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(MovieMapper::map)
                .collect(Collectors.toList());
    }

    public MovieResponseDTO findById(Long id){
        Optional<Movie> movie = movieRepository.findById(id);
        return movie
                .map(MovieMapper::map)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    public List<MovieResponseDTO> findByCategory(Long categoryId){
       List<Movie> movieList = movieRepository.findMovieByCategory(List.of(Category.builder().id(categoryId).build()));
       return movieList.stream()
               .map(MovieMapper::map)
               .collect(Collectors.toList());
    }


    public void deleteById(Long id){
        movieRepository.deleteById(id);
    }

    public MovieResponseDTO createMovie (Movie movieRequest){
        movieRequest.setCategories(findCategories(movieRequest.getCategories()));
        movieRequest.setStreamings(findStreamings(movieRequest.getStreamings()));
        movieRepository.save(movieRequest);
        return MovieMapper.map(movieRequest);
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> {
            Optional<Category> optCategory = categoryService.findById(category.getId());
            optCategory.ifPresent(categoriesFound::add);
        });
        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> {
            Optional<Streaming> streamingOpt = streamingService.findById(streaming.getId());
            streamingOpt.ifPresent(streamingsFound::add);
        });
        return streamingsFound;
    }

    public MovieResponseDTO UpdateMovie(Long id, Movie movie){
        Optional<Movie> verifyMovie = movieRepository.findById(id);
        if (verifyMovie.isPresent()){
            List<Category> categories = this.findCategories(movie.getCategories());
            List<Streaming> streamings = this.findStreamings(movie.getStreamings());

            Movie movieUpt = verifyMovie.get();
            movieUpt.setTitle(movie.getTitle());
            movieUpt.setDescription(movie.getDescription());
            movieUpt.setReleaseDate(movie.getReleaseDate());
            movieUpt.setRating(movie.getRating());

            movieUpt.getStreamings().clear();
            movieUpt.getStreamings().addAll(streamings);

            movieUpt.getCategories().clear();
            movieUpt.getCategories().addAll(categories);

            movieRepository.save(movie);
            return MovieMapper.map(movie);
        }
        return null;
    }
}
