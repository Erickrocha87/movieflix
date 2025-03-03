package com.movieflix.controllers;


import com.movieflix.dto.LoginRequestDTO;
import com.movieflix.dto.LoginResponseDTO;
import com.movieflix.dto.UserRequestDTO;
import com.movieflix.dto.UserResponseDTO;
import com.movieflix.entity.User;
import com.movieflix.infra.config.TokenService;
import com.movieflix.infra.exceptions.UsernameOrPasswordInvalidException;
import com.movieflix.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movieflix/auth")
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register (@RequestBody UserRequestDTO request){
        UserResponseDTO user = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        try{
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);
            User user = (User) authenticate.getPrincipal();
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok().body(new LoginResponseDTO(token));
        }catch (BadCredentialsException e){
            throw new UsernameOrPasswordInvalidException("Invalid email or password");
        }

    }
}
