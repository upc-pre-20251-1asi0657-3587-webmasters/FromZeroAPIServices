package com.authservice.iam.infrastructure.tokens.jwt;

import com.authservice.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface BearerTokenService extends TokenService {
    //String getBearerToken(HttpServletRequest request);
    //String generateToken(Authentication authentication);
}
