package com.my.answers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AnswersApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnswersApplication.class, args);
	}

}
