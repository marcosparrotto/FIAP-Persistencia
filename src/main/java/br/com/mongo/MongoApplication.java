package br.com.mongo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import br.com.mongo.entity.Categoria;
import br.com.mongo.entity.Endereco;
import br.com.mongo.entity.Order;
import br.com.mongo.entity.Pessoa;
import br.com.mongo.entity.Produto;
import br.com.mongo.enums.OrderStatus;
import br.com.mongo.repository.CategoriaRepository;
import br.com.mongo.repository.OrderRepository;
import br.com.mongo.repository.PessoaRepository;
import br.com.mongo.repository.ProdutoRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "br.com.mongo.repository")
public class MongoApplication implements CommandLineRunner {

	@Autowired
	public PessoaRepository pessoaRepository;

	@Autowired
	public ProdutoRepository produtoRepository;

	@Autowired
	public OrderRepository orderRepository;

	@Autowired
	public CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		categoriaRepository.deleteAll();
		produtoRepository.deleteAll();
		pessoaRepository.deleteAll();
		orderRepository.deleteAll();

		Categoria cat1 = new Categoria("Electronics");
		Categoria cat2 = new Categoria("Books");
		Categoria cat3 = new Categoria("Computers");

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		Pessoa pessoa1 = new Pessoa("Maria Brown", "maria@gmail.com", "988888888", new ArrayList<Endereco>(
				Arrays.asList(new Endereco("Av Brasil", "1111"), new Endereco("Av Argentina", "1111"))));

		Pessoa pessoa2 = new Pessoa("Alex Green", "alex@gmail.com", "977777777", new ArrayList<Endereco>(
				Arrays.asList(new Endereco("Rua Bobos", "0"), new Endereco("Rua Aleatorios", "0"))));

		Pessoa pessoa3 = new Pessoa("Anderson", "anderson@anderson", "123", null);

		pessoaRepository.saveAll(Arrays.asList(pessoa1, pessoa2, pessoa3));

		Produto p1 = new Produto("O Senhor dos Aneis 1",
				"A historia narra o conflito contra o mal que se alastra pela Terra-media", 90.5, "", 99);

		Produto p2 = new Produto("Smart TV", "TV Samsung 60polegadas", 2190.0, "", 50);

		Produto p3 = new Produto("Macbook Pro", "Notebook pratico, leve e perfeito pra acompanhar sua rotina", 1250.0,
				"", 25);

		Produto p4 = new Produto("PC Gamer", "Dell G7 Gamer", 1200.0, "", 25);

		Produto p5 = new Produto("Harry Potter e a pedra filosofal",
				"o primeiro dos sete livros da série de fantasia Harry Potter", 100.99, "", 75);

		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		System.out.println("\n\n");
		produtoRepository.findAll().stream().forEach(i -> System.out.println(i));

		Order o1 = new Order(Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID , Arrays.asList(p1, p5));
		Order o2 = new Order(Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT , Arrays.asList(p2, p3,p4));
		Order o3 = new Order(Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT , Arrays.asList(p4, p5));
		
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));

		pessoa1.setOrdens(Arrays.asList(o1,o2));
		pessoa2.setOrdens(Arrays.asList(o3));
		pessoaRepository.saveAll(Arrays.asList(pessoa1,pessoa2));

		pessoaRepository.findAll().stream().forEach(i -> System.out.println(i));

	}
}
