package validator;

import annotation.GEOZipCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GEOZipCodeValidator implements ConstraintValidator<GEOZipCode, String> {
    String[] zipCodes = {"12345", "12121", "00000"};

    @Override
    public void initialize(GEOZipCode constraintAnnotation) {

    }

    @Override
    public boolean isValid(String zipCode, ConstraintValidatorContext constraintValidatorContext) {
        if (zipCode == null) {
            return true;
        }

        for (String i : zipCodes) {
            if (i.equals(zipCode.toString())){
                return true;
            }
        }

        return false;
    }
}
