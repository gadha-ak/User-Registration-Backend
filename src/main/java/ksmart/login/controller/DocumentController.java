package ksmart.login.controller;

import ksmart.login.contract.DocumentRequest;
import ksmart.login.contract.DocumentResponse;
import ksmart.login.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DocumentController {
    @Autowired
    DocumentService documentService;

    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping ("kycotherdocument")
    public ResponseEntity <DocumentResponse> documentVerification (@RequestBody DocumentRequest request) {
        DocumentResponse documentResponse = documentService.verifyOtherDocument (request);
        return ResponseEntity.ok(documentResponse);
    }
}
