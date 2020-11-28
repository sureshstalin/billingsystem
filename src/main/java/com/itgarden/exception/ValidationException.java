package com.itgarden.exception;

import org.springframework.validation.BindingResult;

/*
 * Created by Suresh Stalin on 20 / Oct / 2020.
 */

public class ValidationException extends Exception {

    private BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
