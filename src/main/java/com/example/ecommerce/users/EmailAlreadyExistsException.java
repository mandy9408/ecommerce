package com.example.ecommerce.users;

public class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
