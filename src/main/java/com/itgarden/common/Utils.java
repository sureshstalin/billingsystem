package com.itgarden.common;

import org.springframework.util.StringUtils;

public class Utils {

    public static String getFullName(String firstName,String middleName,String lastName) {

        String fullName = "";
        if(StringUtils.isEmpty(middleName)) {
            fullName = new StringBuilder().append(firstName)
                    .append(" ").append(lastName).toString();
        }else{
            fullName = new StringBuilder().append(firstName).append( " ")
                .append(middleName).append(" ").append(lastName).toString();
        }
        return fullName;
    }
}
