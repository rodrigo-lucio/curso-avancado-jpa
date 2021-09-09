package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		CadastroDeProduto.cadastrarProduto();
		cadastrarCliente();

		EntityManager em = JPAUtil.getEntityManager();
		ClienteDao clienteDao = new ClienteDao(em);
		Cliente cliente = clienteDao.buscarPorId(1L);

		Pedido pedido = new Pedido(cliente);

		em.getTransaction().begin();

		ProdutoDao produtoDao = new ProdutoDao(em);

		pedido.addItem(new ItemPedido(10, pedido, produtoDao.buscarPorId(1L)));
		pedido.addItem(new ItemPedido(1, pedido, produtoDao.buscarPorId(2L)));
		pedido.addItem(new ItemPedido(1, pedido, produtoDao.buscarPorId(3L)));
		pedido.addItem(new ItemPedido(2, pedido, produtoDao.buscarPorId(1L)));

		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);

		em.getTransaction().commit();

		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("Valor Total pedidos: " + totalVendido);
		pedidoDao.relatorioDeVendas()
				.forEach(reg -> System.out.println(String.format("%s - %s - %s", reg[0], reg[1], reg[2])));

	}

	public static void cadastrarCliente() {
		EntityManager em = JPAUtil.getEntityManager();
		Cliente cliente = new Cliente("Rodrigo", "99999999999");
		ClienteDao clienteDao = new ClienteDao(em);
		em.getTransaction().begin();
		clienteDao.cadastrar(cliente);
		em.getTransaction().commit();
		em.close();

	}

}
