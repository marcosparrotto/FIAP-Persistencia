package br.com.mongo.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Pessoa {

	@Id
	private String id;
	private String name;
	private String email;
	private String phone;
	private List<Endereco> address;
	
	@DBRef
	private List<Order> ordens;

	// @Override
    // public String toString() {
    // 	return "\nNome: " + name + " - email: " + email + " - telefone: " + phone + "\nEndereco: " + address;
    // }

	public Pessoa(String name, String email, String phone, List<Endereco> address) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

}
