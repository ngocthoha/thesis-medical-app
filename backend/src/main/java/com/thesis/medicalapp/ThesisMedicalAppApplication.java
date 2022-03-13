package com.thesis.medicalapp;

import com.thesis.medicalapp.models.Role;
import com.thesis.medicalapp.models.User;
import com.thesis.medicalapp.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
@RestController
public class ThesisMedicalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThesisMedicalAppApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUser(new User(null, "John", "john", "1234","john@gmail.com", "Thanh Hoa", "0326185282", new Date(), new ArrayList<>()));

            userService.addRoleToUser("john", "ROLE_USER");

        };
    }
    @GetMapping
    public String hello() {
        return "It works!";
    }
}
