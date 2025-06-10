package ksmart.login.contract;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class UserRegistrationRequest {
    private UUID id;


    @Pattern(regexp = "(^$|\\+?[0-9]{10})", message = "Invalid Phone number")
    private String phoneNumber;

    @Email(message = "Email should be valid")
    @Size(min = 8, max = 50, message = "{validation.email.size}")
    private String email;

    private Boolean countryType;

    private String otp;
}
