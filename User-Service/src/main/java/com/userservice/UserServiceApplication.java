package com.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));  //Eso quitenlo solo lo puse para que no hayan conflictos con los puertos de mi PC
        app.run(args);
        //SpringApplication.run(UserServiceApplication.class, args);
    }

}
