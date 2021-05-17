package com.example.jwtwithsession.dto;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class JwtRequest {

    private @NonNull String username;
    private @NonNull String password;
}
