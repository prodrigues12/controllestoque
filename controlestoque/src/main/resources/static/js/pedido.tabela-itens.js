Controllestoque.TabelaItens = (function() {

	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
	}
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));

	}
	function onItemSelecionado (evento, item){
//		console.log('Item recebido do autocomplete', item);
		
		var resposta = $.ajax({
			url: 'pedido/item',
			method: 'POST',
			data:{
				codigoProduto: item.codigo
			}
		});
		resposta.done(function(data){
			console.log('retorno', data);
		});
	}

	return TabelaItens;
	
}());

$(function(){
	
	var autocomplete = new Controllestoque.Autocomplete();
autocomplete.iniciar();

var tabelaItem = new Controllestoque.TabelaItens(autocomplete);
tabelaItem.iniciar();

})
