var Controllestoque = Controllestoque || {};

Controllestoque.DialogoSalvar = (function() {

	function DialogoSalvar() {
		this.salvarBtn = $('.js-salvar-btn');

	}

	DialogoSalvar.prototype.iniciar = function() {
		this.salvarBtn.on('click', onSalvarClique.bind(this));
	}

	function onSalvarClique() {

		$.ajax({
			url: "/secao",
			method: 'POST',
			concontentType: "application/json",

			success: chamarModalSucesso(),
			error: onErroExcluir()

		});
	}
	function chamarModalSucesso() {
		Swal.fire({
			icon: 'success',
			timer: 5000,
			title: 'Salvo com sucesso?',
			text: 'Deseja realizar outra operação?',
			footer: '<a href="">Why do I have this issue?</a>'
		})
	}

	function onErroExcluir() {
		Swal.fire({
			icon: 'error',
			timer: 5000,
			title: 'Oops...',
			text: 'Não foi possivel salvar!',
			footer: '<a href="">Why do I have this issue?</a>'
		})


	}
	return DialogoSalvar;

}());

$(function() {
	var dialogo = new Controllestoque.DialogoSalvar();
	dialogo.iniciar();
});