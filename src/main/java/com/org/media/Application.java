package com.org.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.out.println("program is starting....");
		SpringApplication.run(Application.class, args);
	}

}
