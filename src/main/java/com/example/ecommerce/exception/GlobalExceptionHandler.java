package com.example.ecommerce.exception;

import com.example.ecommerce.auth.InvalidCredentialsException;
import com.example.ecommerce.auth.UnauthenticatedException;
import com.example.ecommerce.users.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice //Esta clase va a encargarse de errores que ocurran en nuestros controllers.
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class) // Cuando ocurra exactamente este tipo de excepción, usa este método.
    public ResponseEntity<Map<String, Object>> handleEmailAlreadyExists(
            EmailAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "status", 409,
                        "message", ex.getMessage()
                ));
    }

//errores de validación para que @NotBlank y @Email devuelvan algo útil.
    @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, Object>> handleValidationErrors(
        MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult()
            .getFieldErrors()
            .forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of(
                    "status", 400,
                    "errors", errors
            ));
}


@ExceptionHandler(InvalidCredentialsException.class)
public ResponseEntity<Map<String, Object>> handleInvalidCredentials(
        InvalidCredentialsException ex
) {

    return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(Map.of(
                    "status", 401,
                    "message", ex.getMessage()
            ));
}

@ExceptionHandler(UnauthenticatedException.class)
public ResponseEntity<Map<String, Object>> handleUnauthenticated(
        UnauthenticatedException ex
) {
    return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(Map.of(
                    "status", 401,
                    "message", ex.getMessage()
            ));
}
}