package com.fromzero.chatservice.infrastructure.eventpublisher;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsConfig {
    @Bean
    public MappingJackson2MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        converter.setObjectMapper(mapper);

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("com.fromzero.candidatesservice.candidatesManagement.domain.model.events.ChatRoomCreatedEvent",
                com.fromzero.chatservice.domain.model.events.ChatRoomCreatedEvent.class);

        converter.setTypeIdMappings(typeIdMappings);

        return converter;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(
            ConnectionFactory connectionFactory,
            MappingJackson2MessageConverter jacksonJmsMessageConverter) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true); // Enable topic mode
        factory.setMessageConverter(jacksonJmsMessageConverter);
        factory.setErrorHandler(t -> System.err.println("JMS error: " + t.getMessage())); // Optional: error handler
        return factory;
    }
}