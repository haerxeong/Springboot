package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.NotChallengingValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotChallengingValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotChallengingMission {
    String message() default "The mission is already in a challenging state for this member.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}