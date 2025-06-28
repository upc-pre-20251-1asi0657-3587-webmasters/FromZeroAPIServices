package com.fromzero.backend.beans;

import com.fromzero.backend.filters.AuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
public class GatewayBeans {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public AuthFilter authFilter(WebClient.Builder webClientBuilder) {
        return new AuthFilter(webClientBuilder);
    }

    @Bean
    @Profile(value = "oauth2")
    public RouteLocator routeLocatorOauth2(RouteLocatorBuilder builder, AuthFilter authFilter) {
        return builder
                .routes()
                .route(route -> route
                        .path("/api/v1/roles/**")
                        //.filters(filter -> filter.filter(authFilter))
                        .uri("lb://Auth-Service")
                )
                .route(route -> route
                        .path("/api/v1/users/**")
                        //.filters(filter -> filter.filter(authFilter))
                        .uri("lb://Auth-Service")
                )
                .route(route -> route
                        .path("/api/v1/enterprise/**")
                        .uri("lb://user-service")
                )
                .route(route -> route
                        .path("/api/v1/developers/**")
                        .uri("lb://user-service")
                )
                .route(route -> route
                        .path("/api/v1/projects/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://project-service")
                )
                .route(route -> route
                        .path("/api/v1/Projects/{projectId}/deliverables/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://deliverables-service")
                )
                .route(route -> route
                        .path("/api/v1/candidates-management/**")
                        .filters(filter -> filter.filter(authFilter))
                        .uri("lb://candidates-service")
                )
                .route(route -> route
                        .path("/api/v2/support-tickets/**")
                        //.filters(filter -> filter.filter(authFilter))
                        .uri("lb://support-service")
                )
                .route(route -> route
                        .path("/api/v2/notifications/**")
                        //.filters(filter -> filter.filter(authFilter))
                        .uri("lb://notification-service")
                )
                .route(route -> route
                        .path("/api/v1/authentication/**")
                        .uri("lb://Auth-Service")
                )
                .route(route -> route
                        .path("/api/v1/chats/**")
                        //.filters(filter -> filter.filter(authFilter))
                        .uri("lb://Chat-Service")
                )
                .route(route -> route
                        .path("/api/v1/chat-messages/**")
                        //.filters(filter -> filter.filter(authFilter))
                        .uri("lb://Chat-Service")
                )
                .build();
    }
}