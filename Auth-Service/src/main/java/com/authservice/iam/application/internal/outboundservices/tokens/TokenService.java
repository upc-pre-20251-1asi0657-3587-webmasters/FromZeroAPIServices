package com.authservice.iam.application.internal.outboundservices.tokens;

import com.authservice.iam.domain.model.aggregates.User;

public interface TokenService {
    String generateToken(String username);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
}
