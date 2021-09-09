package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.dto.ProdutoPedidoDTO;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

	public List<ProdutoPedidoDTO> relatorioDeVendas() {
		String jpql = "SELECT new br.com.alura.loja.modelo.dto.ProdutoPedidoDTO(produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.dataCadastro)) "
				+ "from Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY 2 DESC";
		return em.createQuery(jpql, ProdutoPedidoDTO.class)
				.getResultList();
	}
 }