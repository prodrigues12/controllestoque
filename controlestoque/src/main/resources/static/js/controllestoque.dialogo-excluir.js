var Controllestoque = Controllestoque || {};

Controllestoque.DialogoExcluir = (function() {
	
	function DialogoExcluir() {
		this.exclusaoBtn = $('.js-exclusao-btn')
	}
	
	DialogoExcluir.prototype.iniciar = function() {
		this.exclusaoBtn.on('click', onExcluirClicado.bind(this));
		
		if (window.location.search.indexOf('excluido') > -1) {
			swal('Pronto!', 'Excluído com sucesso!', 'success');
		}
	}
	
	function onExcluirClicado(evento) {
		event.preventDefault();
		var botaoClicado = $(evento.currentTarget);
		var url = botaoClicado.data('url');
		var objeto = botaoClicado.data('objeto');
		
		swal({
			title: 'Tem certeza?',
			text: 'Realmente deseja excluir " ' + objeto + ' "?  Você não poderá recuperar depois.',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, exclua agora!',
			closeOnConfirm: false
	}, onExclusaoConfirmado(url));

	}
	
	function onExclusaoConfirmado(url) {
		$.ajax({
			url: url,
			method: 'DELETE',
			success: onExcluidoSucesso.bind(this)
//			error: onErroExcluir()
		});
	}
	
	function onExcluidoSucesso() {
//		var urlAtual = window.location.href;
//		var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
//		var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';
//		
//		window.location = novaUrl;
window.location.reload();
	}
	
	function onErroExcluir(e) {
		console.log('ahahahah', e.responseText);
		swal('Oops!', e.responseText, 'error');
	}
	
	return DialogoExcluir;
	
}());

$(function() {
	var dialogo = new Controllestoque.DialogoExcluir();
	dialogo.iniciar();
});