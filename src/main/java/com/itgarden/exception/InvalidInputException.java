package com.itgarden.exception;

import java.util.List;

/*
 * Created by Suresh Stalin on 20 / Oct / 2020.
 */

public class InvalidInputException extends RuntimeException {

    private List<String> errorList;
    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

