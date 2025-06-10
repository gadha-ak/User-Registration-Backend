package ksmart.login.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ksmart.login.model.Login;

public interface LoginRepository extends JpaRepository <Login, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);

    Optional<Login> findByPhoneNumber(@Pattern(regexp = "(^$|\\+?[0-9]{10})", message = "Invalid Phone number") String phoneNumber);

    Optional<Login> findByEmail(@Email(message = "Invalid email format") String emailId);

    Optional<Login> findByAadhaarNo(String aadhaarNo);
}
