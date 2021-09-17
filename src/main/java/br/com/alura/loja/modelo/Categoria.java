package br.com.alura.loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	@EmbeddedId					//Cria chave primaria composta da categoria e nome
	private CategoriaId id;

	public Categoria() {
	}
	
	public Categoria(Long id, String nome) {
		this.id = new CategoriaId(id, nome);
	}

	public String getNome() {
		return id.getNome();
	}
	public void setNome(String nome) {
		this.id.setNome(nome);
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id.getId() + ", nome=" + id.getNome() + "]";
	}
	

}
