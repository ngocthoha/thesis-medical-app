package com.thesis.medicalapp.services.impl;

import com.thesis.medicalapp.models.OTP;
import com.thesis.medicalapp.models.User;
import com.thesis.medicalapp.repository.OTPRepository;
import com.thesis.medicalapp.repository.UserRepository;
import com.thesis.medicalapp.services.OTPService;
import com.thesis.medicalapp.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OTPServiceImpl implements OTPService {
    private final OTPRepository otpRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private static final int EXPIRE_TIME_MINUTES = 2;

    @Override
    public OTP generateOTP(User user) {
        OTP otp = new OTP();
        otp.setToken(otp.generateOTP());
        otp.setUser(user);
        otp.setExpireDate(otp.calculateExpireDate(EXPIRE_TIME_MINUTES));
        log.info("Saving new otp {} to the database", otp.getToken());
        return otpRepository.save(otp);
    }

    @Override
    public void verifyUser(String username, String token) throws Exception {
        User user = userService.findByUsername(username);
        Optional<OTP> otpOp = otpRepository.findByUserIdAndAndToken(user.getId(), token);
        if (!otpOp.isPresent()) {
            throw new Exception("OTP is not correct!");
        }
        OTP otp = otpOp.get();
        if (!otp.isValid()) {
            throw new Exception("OTP is expire!");
        }
        otpRepository.delete(otp);
        user.setEnabled(Boolean.TRUE);
        userRepository.save(user);
    }
}
