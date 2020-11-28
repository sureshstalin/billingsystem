package com.itgarden.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Created by Suresh Stalin on 10 / Nov / 2020.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvalidTokenException extends RuntimeException{

    private String errorMessage;

}
