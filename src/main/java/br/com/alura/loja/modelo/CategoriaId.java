package br.com.alura.loja.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaId implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	
	public CategoriaId(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public CategoriaId() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}