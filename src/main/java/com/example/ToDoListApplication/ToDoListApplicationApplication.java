package com.example.ToDoListApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.example.ToDoListApplication")
@EnableJpaRepositories("com.example.ToDoListApplication")
@EntityScan("com.example.ToDoListApplication")
public class ToDoListApplicationApplication {
	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplicationApplication.class, args);
	}
}