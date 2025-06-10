package ksmart.login.service;

import ksmart.login.contract.LoginRequest;
import ksmart.login.contract.LoginResponse;
import ksmart.login.model.Login;
import ksmart.login.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private static final String OTP = "123456";

    public LoginResponse saveLogin(LoginRequest request) {
        Optional<Login> loginUser = Optional.empty();

        // Validate Phone Number Login
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            loginUser = loginRepository.findByPhoneNumber(request.getPhoneNumber());
        }
        // Validate Email Login
        else if (request.getEmail() != null && !request.getEmail().isBlank()) {
            loginUser = loginRepository.findByEmail(request.getEmail());
        } else {
            return new LoginResponse("Phone number or email must be provided.");
        }

        // If user not found
        if (loginUser.isEmpty()) {
            return new LoginResponse("User not found. Please register.");
        }

        Login user = loginUser.get();

        // OTP Validation
        if (!OTP.equals(request.getOtp())) {
            return new LoginResponse("Invalid OTP. Access denied.");
        }

        // Business Rule Checks
        if (Boolean.FALSE.equals(user.getIsKycVerified())) {
            return new LoginResponse("KYC not verified. Access denied.");
        }

        if (Boolean.FALSE.equals(user.getIsActive())) {
            return new LoginResponse("User is not active. Please complete KYC.");
        }

//        if (Boolean.FALSE.equals(user.getIsFirstLogin())) {
//            return new LoginResponse("KYC verification not completed. Access denied.");
//        }

        return new LoginResponse("Login successful.");
    }

    public boolean checkUserExists(LoginRequest request) {
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            return loginRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent();
        }
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            return loginRepository.findByEmail(request.getEmail()).isPresent();
        }
        return false;
    }
}
