package com.example.shopping;

import com.example.shopping.entity.User;
import com.example.shopping.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository) {
		return args -> {
			final List<User> users = Arrays.asList(
					new User().setUsername("paiizz").setPassword("1234"),
					new User().setUsername("trong").setPassword("1234"),
					new User().setUsername("nack").setPassword("1234")
			);
			users.forEach(it -> {
				userRepository.save(it);
			});

			userRepository.findAll().forEach(System.out::println);
		};
	}

}
