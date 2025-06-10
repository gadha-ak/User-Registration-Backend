package ksmart.login.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DocumentRequest {
    private UUID id;

    private String documentNumber;
    private String documentName;
    private String documentType;
    private String documentDetails;
}
