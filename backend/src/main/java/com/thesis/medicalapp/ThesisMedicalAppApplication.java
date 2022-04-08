package com.thesis.medicalapp;

import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.pojo.ProfileDTO;
import com.thesis.medicalapp.pojo.ScheduleDTO;
import com.thesis.medicalapp.repository.DoctorRepository;
import com.thesis.medicalapp.repository.ProfileRepository;
import com.thesis.medicalapp.repository.ScheduleRepository;
import com.thesis.medicalapp.services.ProfileService;
import com.thesis.medicalapp.services.ScheduleService;
import com.thesis.medicalapp.services.UserService;
import com.thesis.medicalapp.utils.SequenceGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    CommandLineRunner run(UserService userService, ProfileRepository profileRepository, ScheduleRepository scheduleRepository, DoctorRepository doctorRepository) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_DOCTOR"));
            User user = new User(null, "John", "john", "1234","john@gmail.com", "Thanh Hoa", "0326185282", new Date(), new ArrayList<>());
            User doctor = new Doctor(null, "Doctor", "doctor", "1234","doctor@gmail.com", "Thanh Hoa", "0326185282", new Date(), new ArrayList<>(), "NHI KHOA", "CKI");
            User userEntity = userService.saveUser(user);
            Date dateFormat = new Date();
            try {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-30");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            List<String> times = new ArrayList<>();
            times.add("9:00-10:00");
            times.add("10:00-11:00");
            times.add("11:00-12:00");
            userService.saveUser(doctor);
            Doctor doctorEntity = doctorRepository.findDoctorByUsername("doctor");
            scheduleRepository.save(new Schedule(null, dateFormat, "H2", times, doctorEntity));
            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("doctor", "ROLE_USER");
            profileRepository.save(new Profile(null, "1", "john", "address", "phone", new Date(), "job", "identity", "healthy", "folk", "gender", "protector", userEntity));
        };
    }
    @GetMapping
    public String hello() {
        return "It works!";
    }
}
