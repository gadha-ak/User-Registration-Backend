package ksmart.login.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity (name = "user_document")
@Getter
@Setter
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String documentNumber;
    private String documentName;
    private String documentType;
    private String documentDetails;

    @ManyToOne
    @JoinColumn (name = "document_id")
    private Login login;
}
