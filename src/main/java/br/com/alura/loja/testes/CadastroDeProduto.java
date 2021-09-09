package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p.getNome()));
	
		//BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
		//System.out.println("Preco do Produto: " +precoDoProduto);
	}

	public static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria caixasSom = new Categoria("CAIXAS DE SOM");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal(800), celulares );
		Produto caixaDeSom = new Produto("Caixa JBL", "Som Alfo", new BigDecimal(399.99), caixasSom );
		Produto iphone = new Produto("Iphone 11", "Camera TOP", new BigDecimal(5699), celulares);
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(iphone);
		categoriaDao.cadastrar(caixasSom);
		produtoDao.cadastrar(caixaDeSom);
		
		em.getTransaction().commit();
		em.close();
	}

}
