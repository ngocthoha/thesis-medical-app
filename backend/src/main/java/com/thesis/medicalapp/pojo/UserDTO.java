package com.thesis.medicalapp.pojo;

import com.thesis.medicalapp.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String name;
    private String email;
    private String address;
    private String phone;
    private Date dob;
    private String imageUrl;

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .dob(user.getDob())
                .imageUrl(user.getImageUrl())
                .build();
    }
}
