package br.com.casadocodigo.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;

@Model
public class CarrinhoComprasBean {

	@Inject
	LivroDao livroDao;
	
	@Inject
	CarrinhoCompras carrinho;
	
	public String add(Integer id) {
		carrinho.add(new CarrinhoItem(livroDao.buscarPorId(id)));
		return "carrinho?faces-redirect=true";
	}
	
	public List<CarrinhoItem> getItens(){
		return carrinho.getItens();
	}
	
	public void remover(CarrinhoItem item) {
		carrinho.remover(item);
	}
	
}
