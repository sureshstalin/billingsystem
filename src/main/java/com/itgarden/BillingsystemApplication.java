package com.itgarden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import com.itgarden.SystemCodeConfiguration;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class BillingsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingsystemApplication.class, args);
    }


//    @Bean
//    SystemCodeConfiguration systemCodeConfigurer() {
//        return new SystemCodeConfiguration();
//    }


}
