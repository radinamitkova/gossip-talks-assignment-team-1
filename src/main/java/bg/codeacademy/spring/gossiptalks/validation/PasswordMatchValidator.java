package bg.codeacademy.spring.gossiptalks.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatcher.class)
public @interface PasswordMatchValidator {
  String message() default "Password confirmation mismatch";

  Class<?> [] groups() default {};

  Class<? extends Payload> [] payload() default {};
}
