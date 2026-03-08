package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

// Classe principale
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Endpoint simple pour tester
    @RestController
    class HelloWorldController {
        @GetMapping("/")
        public String sayHello() {
            return "Hello, World!";
        }
    }

    // -----------------------
    // ENTITÉ USER
    // -----------------------
    @Entity
    class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String email;

        public User() {}

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    // -----------------------
    // REPOSITORY USER
    // -----------------------
    interface UserRepository extends JpaRepository<User, Long> {
    }

    // -----------------------
    // INITIALISATION DES UTILISATEURS
    // -----------------------
    @Component
    class DataInitializer implements CommandLineRunner {

        private final UserRepository userRepository;

        public DataInitializer(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public void run(String... args) throws Exception {
            userRepository.save(new User("Tacko", "tacko@example.com"));
            userRepository.save(new User("Boye", "boye@example.com"));
            userRepository.save(new User("Coumbis", "coumbis@example.com"));
        }
    }

    // -----------------------
    // CONTROLEUR /api/users
    // -----------------------
    @RestController
    class UserController {

        private final UserRepository userRepository;

        public UserController(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @GetMapping("/api/users")
        public List<User> getUsers() {
            return userRepository.findAll();
        }
    }
}
