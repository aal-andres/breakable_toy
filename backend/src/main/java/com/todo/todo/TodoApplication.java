package com.todo.todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
		LocalDateTime now = LocalDateTime.now().withNano(0);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.println("oaaaaaaaaaaaalll. "+now.format(formatter));
	}

}
