package br.com.alura.loja.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private DadosPessoais dadosPessoais;

	public Cliente() {
	}
	
	public Cliente(String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}

	public void setNome(String nome) {
		this.dadosPessoais.setNome(nome);
	}

	public String getCpf() {
		return dadosPessoais.getCpf();
	}

	public void setCpf(String cpf) {
		this.dadosPessoais.setCpf(cpf);
	}

	public String getNome() {
		return dadosPessoais.getNome();
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + dadosPessoais.getNome() + "]";
	}
	
	
		
}
