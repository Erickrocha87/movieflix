package com.movieflix.controllers;

import com.movieflix.dto.MovieRequestDTO;
import com.movieflix.dto.MovieResponseDTO;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> findAll(){
        List<MovieResponseDTO> movies = movieService.findAll();
        return ResponseEntity
                .ok()
                .body(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> findById(@PathVariable Long id){
        MovieResponseDTO movie = movieService.findById(id);
        return ResponseEntity
                .ok()
                .body(movie);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponseDTO>> findByCategory(@RequestParam Long categoryId){
        List<MovieResponseDTO> movies = movieService.findByCategory(categoryId);
        return ResponseEntity
                .ok()
                .body(movies);
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@Valid @RequestBody MovieRequestDTO movieRequest){
        MovieResponseDTO movie = movieService.createMovie(MovieMapper.map(movieRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieRequestDTO movieRequest){
        MovieResponseDTO movie = movieService.UpdateMovie(id, MovieMapper.map(movieRequest));
        return ResponseEntity
                .ok()
                .body(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
