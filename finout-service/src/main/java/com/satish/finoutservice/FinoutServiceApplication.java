package com.satish.finoutservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
	basePackages = {"com.satish.finoutservice.repo.graph",
			"com.satish.finoutservice.repo.user"
	}
)
//@ComponentScan(basePackages = "com.satish.finoutservice")
public class FinoutServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinoutServiceApplication.class, args);
		System.out.println("-------------Application started---------------------");

	}

}
