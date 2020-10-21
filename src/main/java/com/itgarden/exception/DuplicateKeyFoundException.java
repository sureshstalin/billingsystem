package com.itgarden.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateKeyFoundException extends Exception {

    public DuplicateKeyFoundException(String errorMessage) {
        super(errorMessage);
    }
}
