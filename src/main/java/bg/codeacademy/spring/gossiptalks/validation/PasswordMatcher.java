package bg.codeacademy.spring.gossiptalks.validation;

import bg.codeacademy.spring.gossiptalks.dtos.ChangePasswordRequest;
import bg.codeacademy.spring.gossiptalks.dtos.CreateUserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<PasswordMatchValidator, Object>
{

  @Override
  public void initialize(PasswordMatchValidator constraintAnnotation)
  {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Object request, ConstraintValidatorContext constraintValidatorContext)
  {
    if (request instanceof CreateUserRequest) {
      CreateUserRequest createRequest = (CreateUserRequest) request;
      return createRequest.getPassword().equals(createRequest.getPasswordConfirmation());
    }
    else {
      ChangePasswordRequest changeRequest = (ChangePasswordRequest) request;
      return changeRequest.getPassword().equals(changeRequest.getPasswordConfirmation());
    }
  }
}
