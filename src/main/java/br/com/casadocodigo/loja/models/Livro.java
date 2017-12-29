package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Livro {
	
	/*************
	 * Atributos *
	 *************/	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Lob 
	private String descricao;
	
	private String titulo;	
	
	private BigDecimal preco;
	
	private Integer numeroPaginas;
	
	@ManyToMany
	private List<Autor> autores = new ArrayList<>();
		
	/********************
	 * Getter e Setters *
	 ********************/
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}
	
	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
		
	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	/*************
	 * To String
	 *************/
	@Override
	public String toString() {
		return "Livro [id=" + id + ", descricao=" + descricao + ", titulo=" + titulo + ", preco=" + preco
				+ ", numeroPaginas=" + numeroPaginas + ", autores=" + autores + "]";
	}
	
}
