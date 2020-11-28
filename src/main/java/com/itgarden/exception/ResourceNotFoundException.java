package com.itgarden.exception;

/*
 * Created by Suresh Stalin on 20 / Oct / 2020.
 */

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
