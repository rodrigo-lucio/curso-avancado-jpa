package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		CadastroDeProduto.cadastrarProdutos();
		cadastrarCliente();
		cadastrarPedidoComItens();
		valorTotalTodosPedidos();
	}

	protected static void valorTotalTodosPedidos() {
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDao pedidoDao = new PedidoDao(em);
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("Valor Total pedidos: " + totalVendido);
		pedidoDao.relatorioDeVendas()
				.forEach(reg -> System.out.println(reg.toString()));
	}

	protected static PedidoDao cadastrarPedidoComItens() {
		EntityManager em = JPAUtil.getEntityManager();
		
		ClienteDao clienteDao = new ClienteDao(em);
		Cliente cliente = clienteDao.buscarPorId(1L);

		Pedido pedido = new Pedido(cliente);
		
		Pedido pedidoVideogame = new Pedido(cliente);

		em.getTransaction().begin();

		ProdutoDao produtoDao = new ProdutoDao(em);

		pedido.addItem(new ItemPedido(10, pedido, produtoDao.buscarPorId(1L)));
		pedido.addItem(new ItemPedido(1, pedido, produtoDao.buscarPorId(2L)));
		pedido.addItem(new ItemPedido(1, pedido, produtoDao.buscarPorId(3L)));
		pedido.addItem(new ItemPedido(2, pedido, produtoDao.buscarPorId(1L)));

		pedidoVideogame.addItem(new ItemPedido(2, pedidoVideogame, produtoDao.buscarPorId(4L)));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedidoVideogame);

		em.getTransaction().commit();
		return pedidoDao;
	}

	protected static void cadastrarCliente() {
		EntityManager em = JPAUtil.getEntityManager();
		Cliente cliente = new Cliente("Rodrigo", "99999999999");
		ClienteDao clienteDao = new ClienteDao(em);
		em.getTransaction().begin();
		clienteDao.cadastrar(cliente);
		em.getTransaction().commit();
		em.close();

	}

}
