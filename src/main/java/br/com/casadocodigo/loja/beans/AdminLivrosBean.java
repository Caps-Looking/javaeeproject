package br.com.casadocodigo.loja.beans;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import java.io.IOException;
import java.util.List;

@Model
public class AdminLivrosBean {

	@Inject
	private LivroDao livroDao;

	@Inject
	private AutorDao autorDao;

	@Inject
	private FacesContext context;

	private Livro livro = new Livro();
	
	private Part capaLivro;

	@Transactional
	public String salvar() throws IOException {
		FileSaver fileSaver = new FileSaver();		
		livro.setCapaPath(fileSaver.writeArchive(capaLivro, "livros"));
		
		livroDao.salvar(livro);

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));

		return "/livros/lista?faces-redirect=true";
	}

	

	public List<Autor> getAutores() {
		return autorDao.listar();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Part getCapaLivro() {
		return capaLivro;
	}

		public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}

}
