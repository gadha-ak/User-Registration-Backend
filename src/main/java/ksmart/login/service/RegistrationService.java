package ksmart.login.service;

import jakarta.validation.Valid;
import ksmart.login.contract.UserRegistrationRequest;
import ksmart.login.contract.UserRegistrationResponse;
import ksmart.login.model.Login;
import ksmart.login.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private LoginRepository loginRepository;
    private ModelMapper modelMapper;

    public UserRegistrationResponse saveRegistration(@Valid UserRegistrationRequest request) {
        if (request.getCountryType() == null) {
            throw new IllegalArgumentException("Country type must be specified");
        }

        // CountryType: true = India, false = Abroad
        if (request.getCountryType()) {
            // ✅ Logic for India (true)
            if (request.getPhoneNumber() == null || !request.getPhoneNumber().matches("\\d{10}")) {
                throw new IllegalArgumentException("Valid 10-digit phone number is required for India");
            }

            // Check if phone number already exists
            if (loginRepository.existsByPhoneNumber(request.getPhoneNumber())) {
                throw new IllegalArgumentException("Phone number already registered");
            }

        } else {
            // ✅ Logic for Abroad (false)
            if (request.getEmail() == null || request.getEmail().isBlank()) {
                throw new IllegalArgumentException("Email is required for abroad registration");
            }

            // Check if email already exists
            if (loginRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Email already registered");
            }
        }


        String otpNumber = request.getOtp();
        if (otpNumber == null || otpNumber.isEmpty()) {
            throw new IllegalArgumentException("Invalid or expired OTP");
        }

        Login login = modelMapper.map(request, Login.class);
        Login registeredLogin = loginRepository.save(login);
        modelMapper.map(registeredLogin, UserRegistrationResponse.class);
        String userId = request.getCountryType() ? request.getPhoneNumber() : request.getEmail();
        return new UserRegistrationResponse("User Account Created", userId);
    }

    public boolean isDuplicate(String phoneNumber, String email) {
        if (phoneNumber != null && !phoneNumber.isBlank()) {
            return loginRepository.existsByPhoneNumber(phoneNumber);
        }
        if (email != null && !email.isBlank()) {
            return loginRepository.existsByEmail(email);
        }
        return false;
    }
}
