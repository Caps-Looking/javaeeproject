package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.casadocodigo.loja.models.Livro;

@Stateful
public class LivroDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager manager;

	public void salvar(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> listar() {
		String jpql = "SELECT DISTINCT l FROM Livro l "
				+ "JOIN FETCH l.autores";
		return manager.createQuery(jpql, Livro.class).getResultList();
	}

	public List<Livro> ultimosLancamentos() {
		String jpql = "SELECT l FROM Livro l ORDER BY l.id DESC";		
		return manager.createQuery(jpql, Livro.class).setMaxResults(5).getResultList();
	}

	public List<Livro> demaisLivros() {
		String jpql = "SELECT l FROM Livro l ORDER BY l.id DESC";
		return manager.createQuery(jpql, Livro.class).setFirstResult(5).getResultList();
	}

	public Livro buscarPorId(Integer id) {		
		return manager.find(Livro.class, id);
	}
	
}
