package com.itgarden.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Created by Suresh Stalin on 20 / Oct / 2020.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private String errorMessage;
    private LocalDateTime localDateTime;

}
