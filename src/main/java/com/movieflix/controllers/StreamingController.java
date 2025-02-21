package com.movieflix.controllers;

import com.movieflix.entity.Streaming;
import com.movieflix.repositories.StreamingRepository;
import com.movieflix.services.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public List<Streaming> findAll(){
        return streamingService.findAll();
    }

    @GetMapping("{id}")
    public Streaming findById(@PathVariable Long id){
        return streamingService.findById(id);
    }

    @PostMapping("/save")
    public Streaming createdStreaming(@RequestBody Streaming streaming){
        return streamingService.createStreaming(streaming);
    }

    @PutMapping("/update/{id}")
    public Streaming updatedStreaming(@PathVariable Long id, @RequestBody Streaming streaming){
        return streamingService.updateStreaming(id, streaming);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(Long id){
        streamingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
