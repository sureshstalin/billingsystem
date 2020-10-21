package com.itgarden.exception;

import org.springframework.validation.BindingResult;

public class ValidationException extends Exception {

    private BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
