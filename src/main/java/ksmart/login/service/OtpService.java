package ksmart.login.service;

public interface OtpService {
    boolean validateOtp(String contact, String otp);
}
