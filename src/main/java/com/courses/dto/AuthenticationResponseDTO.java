package com.courses.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDTO {
    private String authenticationToken;
    private String refreshToken;
}
