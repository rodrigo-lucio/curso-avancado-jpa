package br.com.alura.loja.modelo;

import javax.persistence.Entity;

@Entity
public class Informatica extends Produto {

	private String marca;
	private Integer modelo;
		 
	public Informatica() {
	}

	public Informatica(String marca, Integer modelo) {
		this.marca = marca;
		this.modelo = modelo;
	}

	public String getAutor() {
		return marca;
	}

	public void setAutor(String autor) {
		this.marca = autor;
	}

	public Integer getNumeroDePaginas() {
		return modelo;
	}

	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.modelo = numeroDePaginas;
	}
	
}
