package ksmart.login.controller;

import jakarta.validation.Valid;
import ksmart.login.common.Response;
import ksmart.login.contract.LoginRequest;
import ksmart.login.contract.LoginResponse;
import ksmart.login.contract.UserRegistrationRequest;
import ksmart.login.contract.UserRegistrationResponse;
import ksmart.login.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping("check-duplicate")
    public ResponseEntity<Map<String, Boolean>> checkDuplicate(@RequestBody Map<String, String> payload) {
        String phoneNumber = payload.get("phoneNumber");
        String email = payload.get("email");

        boolean exists = registrationService.isDuplicate(phoneNumber, email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping("register")
    public UserRegistrationResponse registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        return registrationService.saveRegistration(request);


    }
}
