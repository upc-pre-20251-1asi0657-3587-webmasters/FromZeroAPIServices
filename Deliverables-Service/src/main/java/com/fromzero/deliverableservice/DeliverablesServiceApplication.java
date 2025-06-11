package com.fromzero.deliverableservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableDiscoveryClient
public class DeliverablesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliverablesServiceApplication.class, args);
    }

}
