package bg.codeacademy.spring.gossiptalks.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler({AccessDeniedException.class})
  public ResponseEntity<Map<String, Object>> handleAccessDeniedExceptions(AccessDeniedException ex)
  {
    ex.printStackTrace();
    Map<String, Object> exceptionBody = new HashMap<>();
    exceptionBody.put("timestamp: ", LocalDateTime.now());
    exceptionBody.put("message: ", ex.getMessage());
    log.warn(ex.getMessage(),ex);
    return new ResponseEntity<>(exceptionBody, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler({NullPointerException.class})
  public ResponseEntity<Map<String, Object>> handleNullPointerExceptions(NullPointerException ex)
  {
    Map<String, Object> exceptionBody = new HashMap<>();
    exceptionBody.put("timestamp: ", LocalDateTime.now());
    exceptionBody.put("message: ", ex.getMessage());
    log.warn(ex.getMessage(),ex);
    return new ResponseEntity<>(exceptionBody, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<Map<String, Object>> handleIllegalArgumentExceptions(IllegalArgumentException ex)
  {
    Map<String, Object> exceptionBody = new HashMap<>();
    exceptionBody.put("timestamp: ", LocalDateTime.now());
    exceptionBody.put("message: ", ex.getMessage());
    return new ResponseEntity<>(exceptionBody, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({InvalidPasswordException.class})
  public ResponseEntity<Map<String, Object>> handleInvalidPasswordException(InvalidPasswordException ex)
  {
    Map<String, Object> exceptionBody = new HashMap<>();
    exceptionBody.put("timestamp: ", LocalDateTime.now());
    exceptionBody.put("message: ", ex.getMessage());
    return new ResponseEntity<>(exceptionBody, HttpStatus.BAD_REQUEST);
  }

}
