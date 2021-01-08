package com.marcos.spring.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcos.spring.entities.Category;
import com.marcos.spring.entities.Order;
import com.marcos.spring.entities.OrderItem;
import com.marcos.spring.entities.Payment;
import com.marcos.spring.entities.Product;
import com.marcos.spring.entities.User;
import com.marcos.spring.entities.enums.OrderStatus;
import com.marcos.spring.repositories.CategoryRepository;
import com.marcos.spring.repositories.OrderItemRepository;
import com.marcos.spring.repositories.OrderRepository;
import com.marcos.spring.repositories.ProductRepository;
import com.marcos.spring.repositories.UserRepository;

@Configuration
@Profile("dev")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "O Senhor dos Aneis 1", "A historia narra o conflito contra o mal que se alastra pela Terra-media", 90.5, "", 99);
		Product p2 = new Product(null, "Smart TV", "TV Samsung 60polegadas", 2190.0, "", 50);
		Product p3 = new Product(null, "Macbook Pro", "Notebook pratico, leve e perfeito pra acompanhar sua rotina", 1250.0, "", 25);
		Product p4 = new Product(null, "PC Gamer", "Dell G7 Gamer", 1200.0, "", 25);
		Product p5 = new Product(null, "Harry Potter e a pedra filosofal", "o primeiro dos sete livros da série de fantasia Harry Potter", 100.99, "", 75);
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "Av Brasil, 1111");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "Rua Bobos, 0");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID , u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT , u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT , u1);
		
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
		
		
	}
}
