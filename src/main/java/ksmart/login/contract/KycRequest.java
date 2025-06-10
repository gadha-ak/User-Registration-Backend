package ksmart.login.contract;

import ksmart.login.validation.Aadhaar;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class KycRequest {
    @Aadhaar
    private String aadhaarNo;
    private String phoneNumber;
    private String email;
    private String otp;
}
