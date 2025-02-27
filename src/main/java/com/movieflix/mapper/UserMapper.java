package com.movieflix.mapper;

import com.movieflix.dto.UserRequestDTO;
import com.movieflix.dto.UserResponseDTO;
import com.movieflix.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public User map(UserRequestDTO request) {
        return User
                .builder()
                .email(request.email())
                .password(request.password())
                .username(request.username())
                .build();
    }

    public UserResponseDTO map(User user) {
        return UserResponseDTO
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }

}
