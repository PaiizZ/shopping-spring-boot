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

	@Bean
	CommandLineRunner demoDataUser(UserRepository userRepository) {
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

	@Bean
	CommandLineRunner demoDataOrder(OrderRepository orderRepository) {
		return args -> {
			final List<Order> orders = Arrays.asList(
					new Order().setUserId((long) 1).setNet(1000).setDiscount(0)
					.setOrderProductList(Arrays.asList(
							new OrderProduct().setProductId((long) 1)
					))
			);
			orders.forEach(it -> {
				orderRepository.save(it);
			});

			orderRepository.findAll().forEach(System.out::println);
		};
	}

	@Bean
	CommandLineRunner demoDataProduct(ProductRepository productRepository) {
		return args -> {
			final List<Product> products = Arrays.asList(
					new Product().setName("Milk").setPrice(25),
					new Product().setName("Chocolate").setPrice(50),
					new Product().setName("Water").setPrice(10)
			);
			products.forEach(it -> {
				productRepository.save(it);
			});

			productRepository.findAll().forEach(System.out::println);
		};
	}

}
