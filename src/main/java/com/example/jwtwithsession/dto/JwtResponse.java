package com.example.jwtwithsession.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private @NonNull String jwtToken;
}
