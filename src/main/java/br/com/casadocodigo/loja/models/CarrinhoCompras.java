package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

import br.com.casadocodigo.loja.daos.CompraDao;
import br.com.casadocodigo.loja.service.PagamentoGateway;

@Named
@SessionScoped
public class CarrinhoCompras implements Serializable {

	@Inject
	CompraDao compraDao;

	@Inject
	PagamentoGateway pagamentoGateway;

	private static final long serialVersionUID = 1L;

	private Set<CarrinhoItem> itens = new HashSet<>();

	public void add(CarrinhoItem item) {
		itens.add(item);
	}

	public List<CarrinhoItem> getItens() {
		return new ArrayList<CarrinhoItem>(itens);
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem carrinhoItem : itens) {
			total = total
					.add(carrinhoItem.getLivro().getPreco().multiply(new BigDecimal(carrinhoItem.getQuantidade())));
		}
		return total;
	}

	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getLivro().getPreco().multiply(new BigDecimal(item.getQuantidade()));
	}

	public void remover(CarrinhoItem item) {
		this.itens.remove(item);
	}

	public Integer getQuantidadeTotal() {
		return itens.stream().mapToInt(item -> item.getQuantidade()).sum();
	}

	public void finalizar(Compra compra) {
		compra.setItens(this.toJson());
		compra.setTotal(this.getTotal());
		compraDao.salvar(compra);
	}

	private String toJson() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		itens.forEach(item -> builder.add(Json.createObjectBuilder().add("titulo", item.getLivro().getTitulo())
				.add("preco", item.getLivro().getPreco()).add("quantidade", item.getQuantidade())
				.add("total", this.getTotal(item))));
		return builder.build().toString();
	}

}
