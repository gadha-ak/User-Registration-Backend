package ksmart.login.controller;

import ksmart.login.contract.LoginRequest;
import ksmart.login.contract.LoginResponse;
import ksmart.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping("/check-user")
    public ResponseEntity<?> checkUser(@RequestBody LoginRequest request) {
        boolean exists = loginService.checkUserExists(request);
        if (exists) {
            return ResponseEntity.ok().body(Map.of("message", "User exists"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found. Please register."));
        }
    }

    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping("login")
    public ResponseEntity<LoginResponse> postLoginUser(@RequestBody LoginRequest request) {
        LoginResponse loginResponseNew = loginService.saveLogin(request);
        return ResponseEntity.ok(loginResponseNew);
    }
}
