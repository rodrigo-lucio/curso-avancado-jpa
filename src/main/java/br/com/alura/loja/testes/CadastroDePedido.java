package br.com.alura.loja.testes;

import java.math.BigDecimal;

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
		Produto produto = produtoDao.buscarPorId(1L);
		pedido.addItem(new ItemPedido(3, pedido, produto));
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		em.getTransaction().commit();
		
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
