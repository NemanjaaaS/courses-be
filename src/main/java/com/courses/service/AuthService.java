package com.courses.service;

import com.courses.dto.AuthenticationRequestDTO;
import com.courses.dto.AuthenticationResponseDTO;
import com.courses.dto.RegisterRequestDTO;

public interface AuthService {

    AuthenticationResponseDTO register(RegisterRequestDTO registerRequestDTO);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO);
    AuthenticationResponseDTO refreshToken(String refreshToken);
    Boolean validateToken(String token);

}
