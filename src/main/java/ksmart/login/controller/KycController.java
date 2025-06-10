package ksmart.login.controller;

import ksmart.login.contract.KycRequest;
import ksmart.login.contract.KycResponse;
import ksmart.login.service.KycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KycController {
    @Autowired
    KycService kycService;

    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping ("kyc")
    public ResponseEntity<KycResponse> kycVerification(@RequestBody KycRequest request) {
        KycResponse kycResponse = kycService.verifiyKyc(request);
        return ResponseEntity.ok(kycResponse);
    }
}
