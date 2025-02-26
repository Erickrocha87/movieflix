package com.movieflix.controllers;

import com.movieflix.dto.StreamingRequestDTO;
import com.movieflix.dto.StreamingResponseDTO;
import com.movieflix.services.StreamingService;
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
        return ResponseEntity
                .ok()
                .body(streamingService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StreamingResponseDTO> createdStreaming(@RequestBody StreamingRequestDTO streaming){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(streamingService.createStreaming(streaming));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponseDTO> updatedStreaming(@PathVariable Long id, @RequestBody StreamingRequestDTO streaming){
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
