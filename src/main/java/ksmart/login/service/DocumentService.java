package ksmart.login.service;

import ksmart.login.contract.DocumentRequest;
import ksmart.login.contract.DocumentResponse;
import ksmart.login.model.Document;
import ksmart.login.model.Login;
import ksmart.login.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DocumentService {
    private LoginRepository loginRepository;
    private ModelMapper modelMapper;

    public DocumentResponse verifyOtherDocument(DocumentRequest request) {
        Login login = loginRepository.findById(request.getId())
                .orElseThrow( () -> new RuntimeException("User not found"));
        modelMapper.map(request, login);
        Document document = modelMapper.map(request, Document.class);
        document.setLogin(login);
        login.getDocument().add(document);
        loginRepository.save(login);
        return new DocumentResponse ("Document verification successful");
    }
}
