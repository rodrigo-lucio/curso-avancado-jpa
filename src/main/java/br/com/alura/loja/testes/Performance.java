package br.com.alura.loja.testes;

import javax.persistence.EntityManager;
import javax.persistence.FetchType;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;

public class Performance {

	public static void main(String[] args) {
		CadastroDeProduto.cadastrarProdutos();
		CadastroDePedido.cadastrarCliente();
		CadastroDePedido.cadastrarPedidoComItens();
		
		EntityManager em = JPAUtil.getEntityManager();
		Pedido pedido = em.find(Pedido.class, 1L);
		System.out.println(pedido);				// Nao traz o cliente, pois ele esta como lazy
		System.out.println(pedido.getItens()); // Faz o select em itens, pois estou solicitando, caso contrario nao faria
		
	}

}
