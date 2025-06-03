package com.authservice.iam.infrastructure.tokens.jwt.services;

import com.authservice.iam.domain.model.aggregates.User;
import com.authservice.iam.infrastructure.tokens.jwt.BearerTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenServiceImpl implements BearerTokenService {
    @Value("${authorization.jwt.secret}")
    private String secret;

    @Value("${authorization.jwt.expiration.days}")
    private int expirationDays;

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String buildTokenWithDefaultParameters(User user) {
        var issuedAt = new Date();
        var expiration = DateUtils.addDays(issuedAt, expirationDays);
        var key = getSigningKey();
        return Jwts.builder().subject(user.getUserEmail()).issuedAt(issuedAt).expiration(expiration).signWith(key).compact();
    }

    @Override
    public String generateToken(User user) {
        return buildTokenWithDefaultParameters(user);
    }
}
