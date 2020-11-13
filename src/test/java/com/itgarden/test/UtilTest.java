package com.itgarden.test;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class UtilTest {

    @Test
    void isBeforeTest() {
        LocalDateTime refreshTokenDate = LocalDateTime.parse("2020-11-11T12:49:49");
        LocalDateTime currentTime = LocalDateTime.parse("2020-11-11T12:48:49");
        System.out.println(currentTime.isAfter(refreshTokenDate));
    }
}
