package com.car_management.service.mail;

import com.car_management.model.user.User;


public interface IEmailService {
    void sendResetPasswordEmail(String email, String otp);
    boolean validateOtp(String otpCode, String email);
    String generateOtp(User user);
}
