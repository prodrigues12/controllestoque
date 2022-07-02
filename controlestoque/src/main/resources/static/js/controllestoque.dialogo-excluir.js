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
		//		evento.preventDefault();
		var botaoClicado = $(evento.currentTarget);
		var url = botaoClicado.data('url');
		var objeto = botaoClicado.data('objeto');

		swal({
			title: "Deseja Excluir?",
			text: 'Realmente deseja excluir "' + objeto + ' " ?  Você não poderá recuperar depois.',
			icon: "warning",
			buttons: [
				'Cancelar',
				'Excluir'
			],
			dangerMode: true,
		})
			.then(function(isConfirm) {

				if (isConfirm) {
					onExclusaoConfirmado(url);

					swal({
						title: 'Excluido com Sucesso!',
						timer: 3000,
						icon: 'success'
					}).then(function() {
						location.reload();
					});

				} else {
					
					swal({
						title: 'Operação Cancelada!',
						timer: 2000,
						icon: 'info'
					}).then(function() {
						
					});
					
					
				}

			});


	}


	function onExclusaoConfirmado(url) {

		$.ajax({
			url: url,
			method: 'DELETE',
			success: onExcluidoSucesso.bind(this),
			error: onErroExcluir.bind(this)

		});
	}

	function onExcluidoSucesso() {
		var urlAtual = window.location.href;
		var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
		var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';


		window.location = novaUrl;




	}

	function onErroExcluir(){

		swal({
			title: 'Ops!',
			text: 'Não possível excluir objeto.',
			timer: 6000,
			icon: 'error'
		}).then(function() {

		});
	}


	return DialogoExcluir;

}());



$(function() {
	var dialogo = new Controllestoque.DialogoExcluir();
	dialogo.iniciar();
});