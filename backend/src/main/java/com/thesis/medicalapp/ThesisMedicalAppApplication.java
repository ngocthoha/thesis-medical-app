package com.thesis.medicalapp;

import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.repository.*;
import com.thesis.medicalapp.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@RestController
@EnableJpaAuditing
public class ThesisMedicalAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThesisMedicalAppApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserService userService, ProfileRepository profileRepository, ScheduleRepository scheduleRepository, DoctorRepository doctorRepository, RoomRepository roomRepository, HospitalRepository hospitalRepository) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_DOCTOR"));
            Hospital hospital = new Hospital(null, "BV Dai Hoc Y Duoc", "address", "info", null, null, null);
            hospitalRepository.save(hospital);
            User user = new User(null, "User", "user", "1234","user@gmail.com", "Thanh Hoa", "0326185282", new Date(), new ArrayList<>(), null);
            User admin = new User(null, "Admin", "admin", "1234","admin@gmail.com", "Thanh Hoa", "0326185282", new Date(), new ArrayList<>(), null);
            User doctor = new Doctor(null, "Doctor", "doctor", "1234","doctor@gmail.com", "Thanh Hoa", "0326185282", new Date(), new ArrayList<>(), null, "Nhi Khoa", "CKI", hospital);
            User userEntity = userService.saveUser(user);
            userService.saveUser(admin);

            Date dateFormat = new Date();
            try {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-12");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            List<String> times = new ArrayList<>();
            times.add("9:00-10:00");
            times.add("10:00-11:00");
            times.add("11:00-12:00");
            userService.saveUser(doctor);
            Doctor doctorEntity = doctorRepository.findDoctorByUsername("doctor");
            Room room = roomRepository.save(new Room(null,"H2", null));
            scheduleRepository.save(new Schedule(null, dateFormat, room, times, doctorEntity));
            userService.addRoleToUser("user", "ROLE_USER");
            userService.addRoleToUser("doctor", "ROLE_DOCTOR");
            userService.addRoleToUser("admin", "ROLE_ADMIN");
            profileRepository.save(new Profile(null, 1L, "user", "address", "phone", new Date(), "job", "identity", "healthy", "folk", "gender", "guardian",null, null, null, userEntity));
        };
    }
    @GetMapping
    public String hello() {
        return "It works!";
    }
}
