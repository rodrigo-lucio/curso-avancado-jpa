package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Informatica;
import br.com.alura.loja.modelo.Livro;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		cadastrarProdutos();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p.getNome()));
	
		List<Produto> buscarPorParametrosComCriteria = produtoDao.buscarPorParametrosComCriteria("PlayStation 5", null, null);
		buscarPorParametrosComCriteria.forEach(e -> System.out.println(e.getDescricao()));
		//BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
		//System.out.println("Preco do Produto: " +precoDoProduto);
	}

	public static void cadastrarProdutos() {
		Categoria celularesCategoria = new Categoria("CELULARES");
		Categoria caixasSomCategoria = new Categoria("CAIXAS DE SOM");
		Categoria videogamesCategoria = new Categoria("CAIXAS DE SOM");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal(800), celularesCategoria );
		Produto caixaDeSom = new Produto("Caixa JBL", "Som Alfo", new BigDecimal(399.99), caixasSomCategoria );
		Produto iphone = new Produto("Iphone 11", "Camera TOP", new BigDecimal(5699), celularesCategoria);
		Produto playstation = new Produto("PlayStation 5", "Videogame TOP", new BigDecimal(5480), videogamesCategoria);
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		Livro livro = new Livro("Robert C Martin", 400);
		livro.setDescricao("Codigo Limpo");
		livro.setPreco(new BigDecimal(50));
		
		Informatica informatica = new Informatica(null, null);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celularesCategoria);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(iphone);
		produtoDao.cadastrar(livro);
		produtoDao.cadastrar(informatica);
		categoriaDao.cadastrar(caixasSomCategoria);
		produtoDao.cadastrar(caixaDeSom);
		categoriaDao.cadastrar(videogamesCategoria);
		produtoDao.cadastrar(playstation);
		
		em.getTransaction().commit();
		em.close();
	}

}
