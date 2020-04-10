package util;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Valid {
    public static <T> Set<ConstraintViolation<T>> validate(T object) {
        ValidatorFactory validatorFactory = javax.validation.Validation.
                buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        return validator.validate(object);
    }
}
