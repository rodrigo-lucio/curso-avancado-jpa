package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;

public class Performance {

	public static void main(String[] args) {
		CadastroDeProduto.cadastrarProdutos();
		CadastroDePedido.cadastrarCliente();
		CadastroDePedido.cadastrarPedidoComItens();
		
		EntityManager em = JPAUtil.getEntityManager();
			
		Pedido pedido = em.find(Pedido.class, 1L);
		System.out.println(pedido);				 // Nao traz o cliente, pois ele esta como lazy
		System.out.println(pedido.getItens());   // Faz o select em itens, pois estou solicitando, caso contrario nao faria
			
		System.out.println(pedido.getCliente().getNome()); // Agora traz o cliente por que eu solicitei, com um select separado
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.buscarPedidoComCliente(1L); 			   	// Caso queira trazer sem ser em select separado, com join no mesmo select
															// Assim, ele simula que o cliente seja EAGER, porem é uma consulta criada com o join fetch
	}	

}
