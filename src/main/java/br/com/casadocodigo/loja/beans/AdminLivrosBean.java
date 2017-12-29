package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {
	
	/****************************
	 * Injeções de dependências *
	 ****************************/
	@Inject
	private LivroDao livroDao;
	
	@Inject
	private AutorDao autorDao;
	
	/*************
	 * Atributos *
	 *************/	
	private Livro livro = new Livro();
	
	private List<Integer> autoresId = new ArrayList<>();	

	/******************
	 * CRUD methods	 * 
	 ******************/
	
	//Salvar
	@Transactional
	public void salvar() {
		autoresId.stream().forEach(a -> livro.getAutores().add(new Autor(a)));
		livroDao.salvar(livro);
		System.out.println("Livro cadastrado: " + livro);
		this.livro = new Livro();
		this.autoresId = new ArrayList<>();
	}
	
	/*********************
	 * Getters e Setters * 
	 *********************/	

	/**
	 * Getters de listas
	 */
	//Get de autores
	public List<Autor> getAutores() {
		return autorDao.listar();
	}	
	
	/**
	 * Outros Getters e Setters
	 */
	//Get de autoresId
	public List<Integer> getAutoresId() {
		return autoresId;
	}

	//Set de autoresId
	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}

	//Get de livro
	public Livro getLivro() {
		return livro;
	}

	//Set de livro
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
}
