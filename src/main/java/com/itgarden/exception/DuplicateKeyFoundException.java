package com.itgarden.exception;

import org.springframework.dao.DataIntegrityViolationException;

/*
 * Created by Suresh Stalin on 20 / Oct / 2020.
 */

public class DuplicateKeyFoundException extends Exception {

    public DuplicateKeyFoundException(String errorMessage) {
        super(errorMessage);
    }
}
