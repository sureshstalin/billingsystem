package com.itgarden.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateKeyFoundException extends DataIntegrityViolationException {

    public DuplicateKeyFoundException(String errorMessage) {
        super(errorMessage);
    }
}
