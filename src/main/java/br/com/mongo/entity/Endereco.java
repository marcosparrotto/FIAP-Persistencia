package br.com.mongo.entity;

import org.springframework.data.annotation.Id;

public class Endereco {

	@Id
	private String id;
	private String rua;
	private String numero;

	public Endereco(String rua, String numero) {
		this.setRua(rua);
		this.setNumero(numero);
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return rua + " - " + numero;
	}
	
}
