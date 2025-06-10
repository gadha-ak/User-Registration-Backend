package ksmart.login.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "k_smart_user")
@Getter
@Setter
//@Table(name = "k_smart_user")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;

    private Integer tenantId;
    private String name;
    private Boolean isActive = false;
    private Boolean isKycVerified = false;
    private UUID aadhaarId;
    private String aadhaarNo;
    private Boolean isFirstLogin = false;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10}$")
    private String whatsappNumber;
    private String userType;
    private Long regNo;
    private Timestamp createdAt;
    private Boolean isDocumentVerified;
    private Timestamp updatedAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String dob;

    private String gender;

    @Column(nullable = false)
    private Boolean countryType = Boolean.TRUE;

    @OneToMany (mappedBy = "login", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> document = new ArrayList<>();

}
