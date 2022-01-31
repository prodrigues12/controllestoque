Controllestoque.TabelaItens = (function() {

	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaProdutoContainer = $('.js-tabela-produto-container');
		this.uuid = $('#uuid').val();
	}
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));

	}
	function onItemSelecionado(evento, item) {
		//		console.log('Item recebido do autocomplete', item);

		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codigoProduto: item.codigo,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizarNoServidor.bind(this));

	}

	function onItemAtualizarNoServidor(html) {
		this.tabelaProdutoContainer.html(html);
		$('.js-tabela-produto-quantidade-item').on('change', onQuantidadeItemAlterado.bind(this));
		$('.js-tabela-item').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
		
	}

	function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val();
		var codigoProduto = input.data('codigo-produto');

		var resposta = $.ajax({
			url: 'item/' + codigoProduto,
			mathod: 'PUT',
			data: {
				quantidade: quantidade,
				uuid:this.uuid
			}
		});
		resposta.done(onItemAtualizarNoServidor.bind(this));
	}

	function onDoubleClick(evento) {
		$(this).toggleClass('solicitando-exclusao');
	}

	function onExclusaoItemClick(evento) {
		var codigoProduto = $(evento.target).data('codigo-cerveja');
		var resposta = $.ajax({
			url: 'item/' + this.uuid + '/' + codigoProduto,
			method: 'DELETE'
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}

	return TabelaItens;

}());

$(function() {

	var autocomplete = new Controllestoque.Autocomplete();
	autocomplete.iniciar();

	var tabelaItem = new Controllestoque.TabelaItens(autocomplete);
	tabelaItem.iniciar();

})
