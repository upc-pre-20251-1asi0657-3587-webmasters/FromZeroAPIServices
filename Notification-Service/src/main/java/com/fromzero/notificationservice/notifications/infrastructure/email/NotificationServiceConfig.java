package com.fromzero.notificationservice.notifications.infrastructure.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NotificationServiceConfig {
    @Bean
    public MappingJackson2MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        converter.setObjectMapper(mapper);

        // Map sender's class name to receiver's class
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put(
            "com.fromzero.deliverableservice.deliverables.domain.events.DeliverableCreatedEvent",
            com.fromzero.notificationservice.notifications.domain.model.events.DeliverableCreatedEvent.class
        );
        typeIdMappings.put(
                "com.userservice.user.domain.model.events.UserCreatedEvent",
                com.fromzero.notificationservice.notifications.domain.model.events.UserCreatedEvent.class
        );
        converter.setTypeIdMappings(typeIdMappings);

        return converter;
    }

    @Bean
    @LoadBalanced

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}