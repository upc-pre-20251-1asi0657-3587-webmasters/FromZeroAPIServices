package com.authservice.iam.infrastructure.authorization.sfs.configuration;

import com.authservice.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.authservice.iam.infrastructure.tokens.jwt.BearerTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    private final BearerTokenService tokenService;
    private final BCryptHashingService hashingService;

    public WebSecurityConfiguration(BearerTokenService tokenService, BCryptHashingService hashingService) {
        this.tokenService = tokenService;
        this.hashingService = hashingService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return hashingService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        var permittedRequestPatterns = new String[] {
                "/api/v1/authentication/**",
                "/v3/api-docs/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/webjars/**"
        };

        http.cors(configurer -> configurer.configurationSource(_ -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("*"));
            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        }));

        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(configurer -> configurer.requestMatchers(permittedRequestPatterns).permitAll().anyRequest().authenticated());

        return http.build();
    }
}
