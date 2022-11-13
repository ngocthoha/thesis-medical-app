package com.thesis.medicalapp;

import com.thesis.medicalapp.config.ESConfig;
import com.thesis.medicalapp.indices.Indices;
import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.models.HospitalService;
import com.thesis.medicalapp.repository.*;
import com.thesis.medicalapp.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
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

@EnableAsync
@SpringBootApplication
@RestController
@EnableJpaAuditing
@EnableScheduling
@EnableJpaRepositories("com.thesis.medicalapp.repository")
public class ThesisMedicalAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThesisMedicalAppApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    CommandLineRunner run(
            UserService userService,
            ProfileRepository profileRepository,
            ScheduleRepository scheduleRepository,
            DoctorRepository doctorRepository,
            RoomRepository roomRepository,
            HospitalRepository hospitalRepository,
            AddressRepository addressRepository,
            HospitalHourRepository hospitalHourRepository,
            ESConfig esConfig,
            HospitalESService hospitalESService,
            DoctorESService doctorESService,
            ServiceRepository serviceRepository,
            ServiceESService serviceESService
    ) {
        return args -> {
            // Remove all data in ES
            esConfig.elasticsearchTemplate().indexOps(IndexCoordinates.of(Indices.HOSPITAL_SERVICE_INDEX)).delete();
            esConfig.elasticsearchTemplate().indexOps(IndexCoordinates.of(Indices.HOSPITAL_INDEX)).delete();
            esConfig.elasticsearchTemplate().indexOps(IndexCoordinates.of(Indices.DOCTOR_INDEX)).delete();

            esConfig.elasticsearchTemplate().indexOps(HospitalES.class).create();
            esConfig.elasticsearchTemplate().indexOps(DoctorES.class).create();
            esConfig.elasticsearchTemplate().indexOps(HospitalServiceES.class).create();
            // Init data
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_DOCTOR"));
            Address address = new Address(null, "Việt Nam", "Hồ Chí Minh", "Quận 5", "Phường 11", "215 Hồng Bàng");
            Address address1 = addressRepository.save(address);
            String hospitalTime = "7:00 - 17:00";
            String hospitalBreak = "12:00 - 13:00";
            HospitalHour hospitalHour = new HospitalHour(null, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak, hospitalTime, hospitalBreak);
            HospitalHour hospitalHour1 = hospitalHourRepository.save(hospitalHour);
            Hospital hospital = new Hospital();
            hospital.setName("Bệnh Viện Đại Học Y Dược HCM");
            hospital.setAddress(address1);
            hospital.setInfo("Bệnh viện có 3 cơ sở, trụ sở chính tại 215 Hồng Bàng, P.11, Q.5. Từ năm 2013, Bệnh viện đưa vào hoạt động tòa nhà 17 tầng đã mở rộng khả năng đáp ứng số lượng người bệnh đến khám ngoại trú và điều trị nội trú tại BV. Đây là tòa nhà thiết kế theo tiêu chuẩn bệnh viện quốc tế, đem đến môi trường khám chữa bệnh tiện nghi, xanh – sạch – đẹp, an ninh giúp người bệnh và gia đình cảm thấy thoải mái hơn khi vào Bệnh viện. Mỗi năm, Bệnh viện tiếp nhận trung bình hơn 2 triệu lượt người khám ngoại trú (khoảng 7.000 người khám/ngày), điều trị nội trú 55.000 người, phẫu thuật khoảng 30.000 trường hợp.");
            hospital.setFavorite(0.0);
            hospital.setRegistrationNumber(0);
            hospital.setIsActive(Boolean.TRUE);
            hospital.setMapImageUrl(null);
            hospital.setHospitalHour(hospitalHour1);
            hospital.setImageUrl("https://ump.edu.vn/img/image-default.png");
            Hospital hospital1 = hospitalRepository.save(hospital);
            hospitalESService.save(hospital1);
            // init service
            HospitalService service = new HospitalService();
            service.setSpecialty(SpecialtyType.CHUAN_DOAN_HINH_ANH);
            service.setType(ScheduleType.ONLINE);
            service.setHospital(hospital1);
            service.setNumOfServicePerHour(2);
            service.setPrice("100.000");
            service.setName("Khám cơ xương khớp");
            service.setImageUrl("https://isofhcare-backup.s3-ap-southeast-1.amazonaws.com/images/20190927_121721_552975_tai-mui-hong-crop-1.max-1800x1800_e35067f6_29c0_47b2_bf9a_0149821990b6.jpg");
            service.setRegistrationNumber(0);
            service.setFavorite(0.0);
            service.setInfo("Dịch vụ Khám Tai - Mũi - Họng theo yêu cầu của Bệnh viện Việt Đức chuyên điều trị các bệnh lý và triệu chứng");
            HospitalService service1 = serviceRepository.save(service);
            serviceESService.save(service1);
            // init user
            String bio = "Là giảng viên của trường Đại học Y dược Thái Nguyên nhiều năm kinh nghiệm, tận tình, nhiệt huyết. Đi đầu trong lĩnh vực dịch vụ y tế tại nhà trong khu vực.";
            User user = new User(null, "user", "+84326185282", "1234", true, null, new ArrayList<>());
            User admin = new User(null, "admin", "+84326185283","1234", true, null, new ArrayList<>());
            User doctor = new Doctor(null, "doctor", "+84326185284","1234", true, null, new ArrayList<>(), "Đinh Ngọc Sơn", Gender.MALE, new Date(), "doctor@gmail.com", SpecialtyType.CHUAN_DOAN_HINH_ANH, "PGS.TS", bio, "100.000", hospital, 0.0);
            User userEntity = userService.saveUser(user);
            userService.saveUser(admin);

            Date dateFormat = new Date();
            try {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-06");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            List<String> times = new ArrayList<>();
            times.add("9:00 - 10:00");
            times.add("10:00 - 11:00");
            times.add("11:00 - 12:00");
            times.add("13:00 - 14:00");
            times.add("14:00 - 15:00");
            userService.saveUser(doctor);
            Doctor doctorEntity = doctorRepository.findDoctorByUsername("doctor");
            doctorESService.save(doctorEntity);
            Room room = roomRepository.save(new Room(null,"H2", null));
            scheduleRepository.save(new Schedule(null, ScheduleType.OFFLINE, dateFormat, room, times, 2, doctorEntity));
            userService.addRoleToUser("user", "ROLE_USER");
            userService.addRoleToUser("doctor", "ROLE_DOCTOR");
            userService.addRoleToUser("admin", "ROLE_ADMIN");
            profileRepository.save(new Profile(null, 1L, "Tho", "Ha Ngoc", address1, "0326185289", "email@gmail.com", new Date(), "Developer", "038200008299", "032288997", "Kinh", Gender.MALE, "guardian","0983839989", "038299988877", "Chủ tài khoản", "relation ship with patient", null, userEntity));
        };
    }
    @GetMapping
    public String hello() {
        return "It works!";
    }
}
