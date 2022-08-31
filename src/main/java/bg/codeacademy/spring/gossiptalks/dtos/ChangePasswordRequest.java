package bg.codeacademy.spring.gossiptalks.dtos;

import bg.codeacademy.spring.gossiptalks.validation.PasswordMatchValidator;

import javax.validation.constraints.NotNull;

@PasswordMatchValidator
public class ChangePasswordRequest
{
  @NotNull
  private String password;
  private String passwordConfirmation;
  private String oldPassword;

  public String getPassword()
  {
    return password;
  }

  public ChangePasswordRequest setPassword(String password)
  {
    this.password = password;
    return this;
  }

  public String getPasswordConfirmation()
  {
    return passwordConfirmation;
  }

  public ChangePasswordRequest setPasswordConfirmation(String passwordConfirmation)
  {
    this.passwordConfirmation = passwordConfirmation;
    return this;
  }

  public String getOldPassword()
  {
    return oldPassword;
  }

  public ChangePasswordRequest setOldPassword(String oldPassword)
  {
    this.oldPassword = oldPassword;
    return this;
  }
}
