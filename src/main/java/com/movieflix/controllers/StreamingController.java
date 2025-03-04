package com.movieflix.controllers;

import com.movieflix.dto.StreamingRequestDTO;
import com.movieflix.dto.StreamingResponseDTO;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.services.StreamingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity<List<StreamingResponseDTO>> findAll(){
        return ResponseEntity
                .ok()
                .body(streamingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponseDTO> findById(@PathVariable Long id){
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok().body(StreamingMapper.map(streaming)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StreamingResponseDTO> createdStreaming(@Valid @RequestBody StreamingRequestDTO streaming){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(streamingService.createStreaming(streaming));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponseDTO> updatedStreaming(@PathVariable Long id, @Valid @RequestBody StreamingRequestDTO streaming){
        return ResponseEntity
                .ok()
                .body(streamingService.updateStreaming(id, streaming));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        streamingService.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
