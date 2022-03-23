package com.controlestoque.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.controlestoque.model.ItemPedido;
import com.controlestoque.model.Produto;

@SessionScope
@Component
public class TabelaItemSession {

	private Set<TabelaItensPedido> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Produto produto, int quantidade) {

		TabelaItensPedido tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(produto, quantidade);
		tabelas.add(tabela);

	}

	public void alterarQuantidadeItem(String uuid, Produto produto, Integer quantidade) {
		TabelaItensPedido tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItem(produto, quantidade);
		
	}

	public void excluirItem(String uuid, Produto produto) {
		TabelaItensPedido tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(produto);
	}

	public List<ItemPedido> getItens(String uuid) {
		return buscarTabelaPorUuid(uuid).getItens();
	}

	private TabelaItensPedido buscarTabelaPorUuid(String uuid) {
		TabelaItensPedido tabela = tabelas.stream().filter(t -> t.getUuid().equals(uuid)).findAny()
				.orElse(new TabelaItensPedido(uuid));
		return tabela;
	}

}
