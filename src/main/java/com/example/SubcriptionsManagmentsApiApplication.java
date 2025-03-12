package com.example;

import org.example.subcriptionsmanagments_api.model.Subscription;
import org.example.subcriptionsmanagments_api.repository.SubscriptionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class SubcriptionsManagmentsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubcriptionsManagmentsApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(SubscriptionRepository repository) {
        return args -> {
            repository.save(new Subscription(null, "Netflix", 39.99, LocalDate.now().plusMonths(1)));
            repository.save(new Subscription(null, "Spotify", 19.99, LocalDate.now().plusMonths(1)));
            System.out.println("📌 Dodano testowe subskrypcje!");
        };
    }
}
