Controllestoque.TabelaItens = (function() {

	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaProdutoContainer = $('.js-tabela-produto-container');
		this.uuid = $('#uuid').val();
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));

	}
	function onItemSelecionado(evento, item) {

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

		var quantidadeItemInput = $('.js-tabela-produto-quantidade-item');
		quantidadeItemInput.on('change', onQuantidadeItemAlterado.bind(this));
//		quantidadeItemInput.maskMoney({ precision: 0, thousands: '' });

		var tabelaItem = $('.js-tabela-item');
		tabelaItem.on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
		
//		this.emitter.trigger('tabela-itens-atualizada', tabelaItem.data('valor-total'));

	}

function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val();
		
		if (quantidade <= 0) {
			input.val(1);
			quantidade = 1;
		}
		
		var codigoProduto = input.data('codigo-produto');
		
		var resposta = $.ajax({
			url: 'item/' + codigoProduto,
			method: 'PUT',
			data: {
				quantidade: quantidade,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizarNoServidor.bind(this));
	}

	function onDoubleClick(evento) {
		$(this).toggleClass('solicitando-exclusao');
	}

	function onExclusaoItemClick(evento) {
		var codigoProduto = $(evento.target).data('codigo-produto');
		var resposta = $.ajax({
			url: 'item/' + this.uuid + '/' + codigoProduto,
			method: 'DELETE'
		});

		resposta.done(onItemAtualizarNoServidor.bind(this));
	}

	return TabelaItens;

}());

$(function() {

	var autocomplete = new Controllestoque.Autocomplete();
	autocomplete.iniciar();

	var tabelaItem = new Controllestoque.TabelaItens(autocomplete);
	tabelaItem.iniciar();

})