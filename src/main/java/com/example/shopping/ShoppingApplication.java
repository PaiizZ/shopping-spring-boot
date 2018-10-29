package com.example.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner demoDataUser(UserController userController, UserRepository userRepository) {
//		return args -> {
//			final List<User> users = Arrays.asList(
//					new User().setUsername("paiizz").setPassword("1234"),
//					new User().setUsername("trong").setPassword("1112"),
//					new User().setUsername("boss").setPassword("1150")
//			);
//			users.forEach(it -> {
//				userController.createUser(it);
//			});
//
//			userRepository.findAll().forEach(System.out::println);
//		};
//	}

//	@Bean
//	CommandLineRunner demoDataProduct(ProductRepository productRepository) {
//		return args -> {
//			final List<Product> products = Arrays.asList(
//					new Product().setName("Milk").setPrice(25),
//					new Product().setName("Chocolate").setPrice(50),
//					new Product().setName("Water").setPrice(10)
//			);
//			products.forEach(it -> {
//				productRepository.save(it);
//			});
//
//			productRepository.findAll().forEach(System.out::println);
//		};
//	}
}
