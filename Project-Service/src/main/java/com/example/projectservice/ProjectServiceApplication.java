package com.example.projectservice;

import com.example.projectservice.projects.domain.model.aggregates.Framework;
import com.example.projectservice.projects.domain.model.aggregates.ProgrammingLanguage;
import com.example.projectservice.projects.infrastructure.persistence.jpa.repositories.FrameworksRepository;
import com.example.projectservice.projects.infrastructure.persistence.jpa.repositories.ProgrammingLanguagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
public class ProjectServiceApplication implements CommandLineRunner {

    @Autowired
    private FrameworksRepository frameworkRepository;

    @Autowired
    private ProgrammingLanguagesRepository programmingLanguageRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        frameworkRepository.save(new Framework("Spring Boot"));
		frameworkRepository.save(new Framework("Vue.js"));
		frameworkRepository.save(new Framework("Angular"));
		frameworkRepository.save(new Framework("Nest.js"));
		frameworkRepository.save(new Framework("Net Core"));
		programmingLanguageRepository.save(new ProgrammingLanguage("Java"));
		programmingLanguageRepository.save(new ProgrammingLanguage("Python"));
		programmingLanguageRepository.save(new ProgrammingLanguage("JavaScript"));
		programmingLanguageRepository.save(new ProgrammingLanguage("C#"));
		programmingLanguageRepository.save(new ProgrammingLanguage("Ruby"));
    }

}
