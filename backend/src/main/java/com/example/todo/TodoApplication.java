package com.example.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    // --- LE MOUCHARD DE DEBUG ---
    @Bean
    CommandLineRunner runner(Environment env) {
        return args -> {
            System.out.println("=================================================");
            System.out.println(">>> DÉMARRAGE DE L'APPLICATION (VERSION DEBUG) <<<");
            
            String mongoUri = env.getProperty("spring.data.mongodb.uri");
            System.out.println("1. URI CONFIGURÉE DANS SPRING : " + mongoUri);
            
            String envVar = System.getenv("SPRING_DATA_MONGODB_URI");
            System.out.println("2. VARIABLE D'ENVIRONNEMENT SYSTÈME : " + envVar);
            
            System.out.println("=================================================");
        };
    }
}