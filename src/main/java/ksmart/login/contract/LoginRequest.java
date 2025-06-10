package ksmart.login.contract;



import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private UUID id;

    @Pattern(regexp = "(^$|\\+?[0-9]{10})", message = "Invalid Phone number")
    private String phoneNumber;

    @Email(message = "Invalid email format")
    private String email;

    private String otp;

    private List<DocumentRequest> document;
}



