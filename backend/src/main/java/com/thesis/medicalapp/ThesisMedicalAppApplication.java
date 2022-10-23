package com.thesis.medicalapp;

import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.repository.*;
import com.thesis.medicalapp.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
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
@EnableScheduling
public class ThesisMedicalAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThesisMedicalAppApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserService userService, ProfileRepository profileRepository, ScheduleRepository scheduleRepository, DoctorRepository doctorRepository, RoomRepository roomRepository, HospitalRepository hospitalRepository, AddressRepository addressRepository, HospitalHourRepository hospitalHourRepository, UserRepository userRepository) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_DOCTOR"));
            Address address = new Address(null, "Viet Nam", "Tinh", "Huyen", "Xa", "");
            Address address1 = addressRepository.save(address);
            String hospitalTime = "7:00 - 17:00";
            String hospitalBreak = "12:00 - 13:00";
            HospitalHour hospitalHour = new HospitalHour(null, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak);
            HospitalHour hospitalHour1 = hospitalHourRepository.save(hospitalHour);
            Hospital hospital = new Hospital(null, "BV Dai Hoc Y Duoc", address1, "info", 0, null, null, true, hospitalHour1);
            hospitalRepository.save(hospital);
            String bio = "Là giảng viên của trường Đại học Y dược Thái Nguyên nhiều năm kinh nghiệm, tận tình, nhiệt huyết. Đi đầu trong lĩnh vực dịch vụ y tế tại nhà trong khu vực.";
            User user = new User(null, "user", "+84326185282", "1234", true, null, new ArrayList<>());
            User admin = new User(null, "admin", "+84326185283","1234", true, null, new ArrayList<>());
            User doctor = new Doctor(null, "doctor", "+84326185284","1234", true, null, new ArrayList<>(), "Doctor Name", Gender.MALE, new Date(), "doctor@gmail.com", SpecialtyType.CHUAN_DOAN_HINH_ANH, "CKI", bio, "100.000", hospital);
            User userEntity = userService.saveUser(user);
            userService.saveUser(admin);

            Date dateFormat = new Date();
            try {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-12");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            List<String> times = new ArrayList<>();
            times.add("9:00 - 10:00");
            times.add("10:00 - 11:00");
            times.add("11:00 - 12:00");
            userService.saveUser(doctor);
            Doctor doctorEntity = doctorRepository.findDoctorByUsername("doctor");
            Room room = roomRepository.save(new Room(null,"H2", null));
            scheduleRepository.save(new Schedule(null, ScheduleType.OFFLINE, dateFormat, room, times, 2, doctorEntity));
            userService.addRoleToUser("user", "ROLE_USER");
            userService.addRoleToUser("doctor", "ROLE_DOCTOR");
            userService.addRoleToUser("admin", "ROLE_ADMIN");
            profileRepository.save(new Profile(null, 1L, "Tho", "Ha Ngoc", address1, "phone", "email@gmail.com", new Date(), "job", "identity", "healthy", "folk", Gender.MALE, "guardian","guardian phone", "guardian identity card", "relationship", "relation ship with patient", null, userEntity));
        };
    }
    @GetMapping
    public String hello() {
        return "It works!";
    }
}
