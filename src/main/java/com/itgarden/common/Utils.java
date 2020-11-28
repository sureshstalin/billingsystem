package com.itgarden.common;

import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/*
 * Created by Suresh Stalin on 18 / Oct / 2020.
 */
public class Utils {

    public static String getFullName(String firstName,String middleName,String lastName) {

        String fullName = null;
        if(StringUtils.isEmpty(firstName) && StringUtils.isEmpty(middleName) && StringUtils.isEmpty(lastName)) {
            return fullName;
        }
        if(StringUtils.isEmpty(middleName)) {
            fullName = new StringBuilder().append(firstName)
                    .append(" ").append(lastName).toString();
        }else{
            fullName = new StringBuilder().append(firstName).append( " ")
                .append(middleName).append(" ").append(lastName).toString();
        }
        return fullName;
    }

    public static LocalDateTime currentDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDateTime convertToLocalDateTime(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return date;
    }
}
