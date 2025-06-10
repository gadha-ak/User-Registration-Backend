package ksmart.login.service;

import ksmart.login.contract.KycRequest;
import ksmart.login.contract.KycResponse;
import ksmart.login.model.Login;
import ksmart.login.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class KycService {
    private final LoginRepository loginRepository;
    private final ModelMapper modelMapper;
    private static final String OTP = "123456";

    public KycResponse verifiyKyc(KycRequest request) {
        Optional<Login> userInfo;

        // Find user by phone number or email
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            userInfo = loginRepository.findByPhoneNumber(request.getPhoneNumber());
        } else if (request.getEmail() != null && !request.getEmail().isBlank()) {
            userInfo = loginRepository.findByEmail(request.getEmail());
        } else {
            return new KycResponse("Phone number or Email ID required", false);
        }

        if (userInfo.isEmpty()) {
            return new KycResponse("No user found", false);
        }

        if (request.getAadhaarNo() == null || !request.getAadhaarNo().matches("\\d{12}")) {
            return new KycResponse("Invalid Aadhaar number", false);
        }

        Optional<Login> aadhaarExists = loginRepository.findByAadhaarNo(request.getAadhaarNo());
        if (aadhaarExists.isPresent()) {
            return new KycResponse("Aadhaar number already registered with another user", false);
        }

        if (!OTP.equals(request.getOtp())) {
            return new KycResponse("Invalid OTP", false);
        }

        // Update existing user with Aadhaar and isKycVerified
        Login user = userInfo.get();
        user.setAadhaarNo(request.getAadhaarNo());
        user.setIsKycVerified(true);
        user.setIsActive(true);
        //user.setIsFirstLogin(true);
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        loginRepository.save(user);

        return new KycResponse("KYC Verification successful", true);
    }
}
