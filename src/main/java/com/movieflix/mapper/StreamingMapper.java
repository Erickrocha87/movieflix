package com.movieflix.mapper;

import com.movieflix.dto.StreamingRequestDTO;
import com.movieflix.dto.StreamingResponseDTO;
import com.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public Streaming map(StreamingRequestDTO requestDTO){
        return Streaming
                .builder()
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
