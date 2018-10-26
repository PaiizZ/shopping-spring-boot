package com.example.shopping;

import com.example.shopping.entity.Order;
import com.example.shopping.entity.OrderProduct;
import com.example.shopping.entity.Product;
import com.example.shopping.entity.User;
import com.example.shopping.repositories.OrderRepository;
import com.example.shopping.repositories.ProductRepository;
import com.example.shopping.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

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
