package ksmart.login.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AadhaarValidator implements ConstraintValidator<Aadhaar, String> {
    private static final String AADHAAR_REGEX = "^[2-9]{1}[0-9]{11}$"; 

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        // Remove spaces if any
        String aadhaarNumber = value.replaceAll("\\s+", "");

        // Aadhaar must be 12 digits and start from 2-9
        return aadhaarNumber.matches(AADHAAR_REGEX);
    }

}
