package bg.codeacademy.spring.gossiptalks.exceptions;

public class InvalidPasswordException extends Exception
{

  public InvalidPasswordException(String message)
  {
    super(message);
  }

  public InvalidPasswordException()
  {
    super("Invalid password!");
  }
}