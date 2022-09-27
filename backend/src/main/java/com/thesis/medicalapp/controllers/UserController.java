package com.thesis.medicalapp.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.medicalapp.models.*;
import com.thesis.medicalapp.payload.SignupRequest;
import com.thesis.medicalapp.payload.VerificationRequest;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.pojo.UserDTO;
import com.thesis.medicalapp.services.OTPService;
import com.thesis.medicalapp.services.SmsService;
import com.thesis.medicalapp.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    private final SmsService smsService;
    private final OTPService otpService;

    @GetMapping("/users/all")
    public ResponseEntity<ApiResponse>getAllUser() {
        try {
            List<UserDTO> userDTOS = userService.getUsers();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", userDTOS)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getUsers() {
        try {
            List<UserDTO> userDTOS = userService.findAllByRoles_Name("ROLE_USER");
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "Success", userDTOS)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
    @PostMapping("/auth/register")
    public ResponseEntity<Object>createUser(@RequestBody @Valid SignupRequest signupRequest) {
        String username = signupRequest.getUsername();
        if (userService.existsByUsername(username)) {
                User user_request = userService.getUser(username);
                if (user_request.getEnabled())
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new ApiResponse<>(0, "Username is already taken!", null)
                    );
        }
        User userDB = new User();
        if (userService.existsByPhone(signupRequest.getPhone())) {
            userDB = userService.getUser(username);
            if (userDB.getEnabled())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, "Phone number is already taken!", null)
                );
        } else {
            User user = new User(
                    null,
                    signupRequest.getUsername(),
                    signupRequest.getPhone(),
                    signupRequest.getPassword(),
                    false,
                    null,
                    new ArrayList<>()
            );
            userDB = userService.saveUser(user);
            String role = "";
            if (signupRequest.getRole() == null) role = "ROLE_USER";
            else role = signupRequest.getRole();
            userService.addRoleToUser(username, role);
        }
        try {
            OTP otp = new OTP();
            otp.setToken(OTP.generateOTP());
            OTP otpDB = otpService.generateOTP(userDB);
            Sms sms = new Sms();
            sms.setTo(signupRequest.getPhone());
            sms.setMessage(otpDB.getToken());
            smsService.sendSMS(sms);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>(1, "User registered successfully!", null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(0, e.getMessage(), null)
            );
        }
    }
    @PostMapping("/auth/register/verify")
    public ResponseEntity<ApiResponse> verifyUser(@RequestBody @Valid VerificationRequest verificationRequest) {
        try {
            String token = verificationRequest.getOtp();
            otpService.verifyUser(verificationRequest.getUsername(), token);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(1, "Verify Successfully!", null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(0, e.getMessage(), null)
            );
        }
    }

    @PatchMapping(value ="/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> partialUpdateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        try {
            UserDTO userRes = userService.partialUpdateUser(id, userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(1, "User updated successfully!", userRes)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(0, e.getMessage(), null)
            );
        }
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getPhone())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("fresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            }

        } else {
            throw new RuntimeException("Refresh toke is missing");
        }
    }
}
@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
