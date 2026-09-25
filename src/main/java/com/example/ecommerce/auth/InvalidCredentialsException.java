package com.example.ecommerce.auth;

public class InvalidCredentialsException extends RuntimeException {

        public InvalidCredentialsException(String message) {
        super(message);
    }
    
}
