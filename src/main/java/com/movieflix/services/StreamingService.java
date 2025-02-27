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

    private final StreamingRepository streamingRepository;

    public List<StreamingResponseDTO> findAll(){
       List<Streaming> list = streamingRepository.findAll();
        return list
                .stream()
                .map(StreamingMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<Streaming> findById(Long id){
        return streamingRepository.findById(id);
    }

    public StreamingResponseDTO createStreaming(StreamingRequestDTO streamingRequestDTO){
        Streaming streaming = StreamingMapper.map(streamingRequestDTO);
        streamingRepository.save(streaming);
        return StreamingMapper.map(streaming);
    }

    public void deleteById(Long id){
        streamingRepository.deleteById(id);
    }

    public StreamingResponseDTO updateStreaming(Long id, StreamingRequestDTO streamingRequestDTO){
        Streaming updatedStreaming = new Streaming();
        Optional<Streaming> verfiryStreaming = streamingRepository.findById(id);
        if(verfiryStreaming.isPresent()){
            updatedStreaming.setId(id);
            updatedStreaming.setName(streamingRequestDTO.name());
            streamingRepository.save(updatedStreaming);
            return StreamingMapper.map(updatedStreaming);
        }
        return null;
    }

}
