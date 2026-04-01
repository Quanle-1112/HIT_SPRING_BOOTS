package org.example.btvn3.exception;

public class DuplicateResourceException extends AppException {
    public DuplicateResourceException(String message) {
        super(409, message);
    }
}