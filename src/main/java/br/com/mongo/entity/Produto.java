package br.com.mongo.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Produto {
	
	@Id
	private String id;

	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	private Integer quantity;

	@DBRef
	private Set<Categoria> categories = new HashSet<>();

	public Produto(String name, String description, Double price, String imgUrl, Integer quantity,
			Set<Categoria> categories) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.quantity = quantity;
		this.categories = categories;
	}

	public Produto(String name, String description, Double price, String imgUrl, Integer quantity) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.quantity = quantity;
	}

	public Produto() {
	}

	@Override
	public String toString(){
		return "Nome: " + name + " - Descricao: " + description + " - Preco: " + price + " - Quantidade: " + quantity;
	}

}
