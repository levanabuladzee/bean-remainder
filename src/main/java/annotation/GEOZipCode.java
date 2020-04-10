package annotation;

import validator.GEOZipCodeValidator;

import javax.validation.Constraint;
import javax.validation.ConstraintTarget;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = GEOZipCodeValidator.class)
@Target(value = {ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
@Repeatable(GEOZipCode.List.class)
public @interface GEOZipCode {
    String message() default "There is a zip code error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        GEOZipCode[] value();
    }
}
