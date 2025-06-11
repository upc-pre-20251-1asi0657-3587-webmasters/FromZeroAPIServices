package com.fromzero.backend;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient

public class SupportServiceApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SupportServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
