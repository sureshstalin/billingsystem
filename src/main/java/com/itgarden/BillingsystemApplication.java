package com.itgarden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@SpringBootApplication
public class BillingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingsystemApplication.class, args);
	}


//	@Bean
//	SystemCodeConfigurer systemCodeConfigurer() {
//		return new SystemCodeConfigurer();
//	}
}
