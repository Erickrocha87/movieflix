package com.movieflix.services;

import com.movieflix.dto.MovieRequestDTO;
import com.movieflix.dto.MovieResponseDTO;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieMapper movieMapper;

    private final MovieRepository movieRepository;

    public List<MovieResponseDTO> findAll(){
        List<Movie> movieList = movieRepository.findAll();
        return movieList
                .stream()
                .map(movieMapper::map)
                .collect(Collectors.toList());
    }

    public MovieResponseDTO findById(Long id){
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(movieMapper::map).orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    public void deleteById(Long id){
        movieRepository.deleteById(id);
    }

    public MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO){
        Movie movie = movieMapper.map(movieRequestDTO);
        movieRepository.save(movie);
        return movieMapper.map(movie);
    }

    public MovieResponseDTO UpdateMovie(Long id, MovieRequestDTO movieRequestDTO){
        Movie updatedMovie = new Movie();
        Optional<Movie> verifyMovie = movieRepository.findById(id);
        if (verifyMovie.isPresent()){
            updatedMovie.setId(id);
            updatedMovie = movieMapper.map(movieRequestDTO);
            movieRepository.save(updatedMovie);
            return movieMapper.map(updatedMovie);
        }
        return null;
    }


}
