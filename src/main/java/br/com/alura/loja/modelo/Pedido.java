package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dataCadastro = LocalDate.now();

	@ManyToOne(fetch = FetchType.LAZY) // Se nao deixarmos o fetch = FetchType.LAZY - ele sempre vai fazer o left join // no cliente )							
	private Cliente cliente; // (tudo que ? ToOne ele faz isso por padr?o se nao definir

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // Ao cadastrar pedido, ja cadastra os itens
	private List<ItemPedido> itens = new ArrayList<>();

	@Column(name = "valor_total")
	private BigDecimal valorTotal = new BigDecimal(0);

	public Pedido() {
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void addItem(ItemPedido item) {
		item.setPedido(this);
		this.itens.add(item);
		this.valorTotal = this.valorTotal.add(item.getPrecoTotal());
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", dataCadastro=" + dataCadastro + ", cliente=" + cliente + ", valorTotal="
				+ valorTotal + "]";
	}

}
