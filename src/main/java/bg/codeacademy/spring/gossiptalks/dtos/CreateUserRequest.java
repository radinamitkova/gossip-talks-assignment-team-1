package bg.codeacademy.spring.gossiptalks.dtos;

import bg.codeacademy.spring.gossiptalks.validation.PasswordMatchValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PasswordMatchValidator
public class CreateUserRequest
{
  @NotNull
  @Email
  private String  email;
  @NotNull
  @Pattern(regexp = "^[a-z0-8\\.\\-]+$")
  private String  username;
  private String  name;
  private Boolean following;
  @NotNull
  private String  password;
  @NotNull
  private String  passwordConfirmation;

  public String getEmail()
  {
    return email;
  }

  public CreateUserRequest setEmail(String email)
  {
    this.email = email;
    return this;
  }

  public String getUsername()
  {
    return username;
  }

  public CreateUserRequest setUsername(String username)
  {
    this.username = username;
    return this;
  }

  public String getName()
  {
    return name;
  }

  public CreateUserRequest setName(String name)
  {
    this.name = name;
    return this;
  }

  public Boolean isFollowing()
  {
    return following;
  }

  public CreateUserRequest setFollowing(Boolean following)
  {
    this.following = following;
    return this;
  }

  public String getPassword()
  {
    return password;
  }

  public CreateUserRequest setPassword(String password)
  {
    this.password = password;
    return this;
  }

  public String getPasswordConfirmation()
  {
    return passwordConfirmation;
  }

  public CreateUserRequest setPasswordConfirmation(String passwordConfirmation)
  {
    this.passwordConfirmation = passwordConfirmation;
    return this;
  }

  @Override
  public String toString()
  {
    return "CreateUserRequest{" +
        "email='" + email + '\'' +
        ", username='" + username + '\'' +
        ", name='" + name + '\'' +
        ", following=" + following +
        ", password='" + password + '\'' +
        ", passwordConfirmation='" + passwordConfirmation + '\'' +
        '}';
  }
}
