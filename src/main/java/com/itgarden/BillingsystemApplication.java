package com.itgarden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.itgarden.SystemCodeConfiguration;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@SpringBootApplication
public class BillingsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingsystemApplication.class, args);
    }


//    @Bean
//    SystemCodeConfiguration systemCodeConfigurer() {
//        return new SystemCodeConfiguration();
//    }
}
