package ksmart.login.contract;

import ksmart.login.validation.Aadhaar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class KycResponse {
    private String message;
    private Boolean kycStatus;

}
