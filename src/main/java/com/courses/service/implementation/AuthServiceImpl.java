package com.courses.service.implementation;

import com.courses.common.service.JwtService;
import com.courses.dto.AuthenticationRequestDTO;
import com.courses.dto.AuthenticationResponseDTO;
import com.courses.dto.RegisterRequestDTO;
import com.courses.models.User;
import com.courses.models.enums.Role;
import com.courses.repositories.UserRepository;
import com.courses.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        User user = User.builder()
                .firstName(registerRequestDTO.getFirstName())
                .lastName(registerRequestDTO.getLastName())
                .email(registerRequestDTO.getEmail())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return AuthenticationResponseDTO.builder()
                .authenticationToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDTO.getEmail(),
                        authenticationRequestDTO.getPassword()
                )
        );
        User user = userRepository.findByEmail(authenticationRequestDTO.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return AuthenticationResponseDTO.builder()
                .authenticationToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponseDTO refreshToken(String refreshToken) {

        User user = userRepository.findByEmail(jwtService.getEmailFromToken(refreshToken)).orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));
        String jwtToken = jwtService.generateToken(user);
        String newRefreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return AuthenticationResponseDTO.builder()
                .authenticationToken(jwtToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    @Override
    public Boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }

}
