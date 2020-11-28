package com.itgarden.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/*
 * Created by Suresh Stalin on 20 / Oct / 2020.
 */


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidationMessage {

    private String validationMessage;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;
}
