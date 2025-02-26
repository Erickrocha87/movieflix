package com.movieflix.mapper;

import com.movieflix.dto.StreamingRequestDTO;
import com.movieflix.dto.StreamingResponseDTO;
import com.movieflix.entity.Streaming;
import org.springframework.stereotype.Component;

@Component
public class StreamingMapper {

    public Streaming map(StreamingRequestDTO requestDTO){
        return Streaming
                .builder()
                .id(requestDTO.id())
                .name(requestDTO.name())
                .build();
    }

    public StreamingResponseDTO map(Streaming streaming){
        return StreamingResponseDTO
                .builder()
                .name(streaming.getName())
                .build();
    }
}
