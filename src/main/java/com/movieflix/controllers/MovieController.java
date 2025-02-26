package com.movieflix.controllers;

import com.movieflix.dto.MovieRequestDTO;
import com.movieflix.dto.MovieResponseDTO;
import com.movieflix.services.MovieService;
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

    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieRequestDTO movieRequest){
        MovieResponseDTO movie = movieService.createMovie(movieRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDTO movieRequest){
        MovieResponseDTO movie = movieService.UpdateMovie(id, movieRequest);
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
