package com.movieflix.services;

import com.movieflix.dto.StreamingRequestDTO;
import com.movieflix.dto.StreamingResponseDTO;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.repositories.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingMapper streamingMapper;

    private final StreamingRepository streamingRepository;

    public List<StreamingResponseDTO> findAll(){
       List<Streaming> list = streamingRepository.findAll();
        return list
                .stream()
                .map(streamingMapper::map)
                .collect(Collectors.toList());
    }

    public StreamingResponseDTO findById(Long id){
        Optional<Streaming> streaming = streamingRepository.findById(id);
        return streaming
                .map(streamingMapper::map)
                .orElseThrow(() -> new RuntimeException("Stream Not Found. Id: " + id));
    }

    public StreamingResponseDTO createStreaming(StreamingRequestDTO streamingRequestDTO){
        Streaming streaming = streamingMapper.map(streamingRequestDTO);
        streamingRepository.save(streaming);
        return streamingMapper.map(streaming);
    }

    public void deleteById(Long id){
        streamingRepository.deleteById(id);
    }

    public StreamingResponseDTO updateStreaming(Long id, StreamingRequestDTO streamingRequestDTO){
        Streaming updatedStreaming = new Streaming();
        Optional<Streaming> verfiryStreaming = streamingRepository.findById(id);
        if(verfiryStreaming.isPresent()){
            updatedStreaming.setId(id);
            updatedStreaming = streamingMapper.map(streamingRequestDTO);
            streamingRepository.save(updatedStreaming);
            return streamingMapper.map(updatedStreaming);
        }
        return null;
    }

}
