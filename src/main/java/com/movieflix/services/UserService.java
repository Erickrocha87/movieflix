package com.movieflix.services;


import com.movieflix.dto.UserRequestDTO;
import com.movieflix.dto.UserResponseDTO;
import com.movieflix.entity.User;
import com.movieflix.mapper.UserMapper;
import com.movieflix.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserResponseDTO register (UserRequestDTO request) {
        User user = UserMapper.map(request);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return UserMapper.map(user);
    }
}
