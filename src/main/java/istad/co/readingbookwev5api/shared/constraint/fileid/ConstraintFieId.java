package istad.co.readingbookwev5api.shared.constraint.fileid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ConstraintFileIdValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface ConstraintFieId {

    String message() default "File Id does not exist.";

    Class<?> [] groups() default {};

    Class<? extends Payload> [] payload() default {};
}
