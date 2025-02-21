package com.movieflix.services;

import com.movieflix.entity.Streaming;
import com.movieflix.repositories.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public List<Streaming> findAll(){
        return streamingRepository.findAll();
    }

    public Streaming findById(Long id){
        Optional<Streaming> streaming = streamingRepository.findById(id);
        return streaming.orElseThrow(() -> new RuntimeException("Stream Not Found. Id: " + id));
    }

    public Streaming createStreaming(Streaming streaming){
        return streamingRepository.save(streaming);
    }

    public void deleteById(Long id){
        streamingRepository.deleteById(id);
    }

    public Streaming updateStreaming(Long id, Streaming streaming){
        Streaming updatedStreaming = new Streaming();
        Optional<Streaming> verfiryStreaming = streamingRepository.findById(id);
        if(verfiryStreaming.isPresent()){
            updatedStreaming.setId(id);
            updatedStreaming.setName(streaming.getName());
            streamingRepository.save(updatedStreaming);
            return updatedStreaming;
        }
        return null;
    }

}
