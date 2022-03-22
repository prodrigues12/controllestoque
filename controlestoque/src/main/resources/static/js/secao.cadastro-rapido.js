var Controlestoque = Controlestoque || {};

Controlestoque.SecaoCadastroRapido = (function() {


	function SecaoCadastroRapido() {

		this.modal = $('#secaoModal');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-secao-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeSecao = $('#nomeSecao');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-secao');
	}

	SecaoCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });// enter no form não submit
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}

	function onModalShow() {
		this.inputNomeSecao.focus();
	}

	function onModalClose() {
		this.inputNomeSecao.val('');
		this.containerMensagemErro.addClass('');
		this.form.find('.form-control-label').removeClass('has-error');
	}

	function onBotaoSalvarClick() {
		
		var nomeSecao = this.inputNomeSecao.val().trim();  
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeSecao }),
			error: onErroSalvandoSecao.bind(this),
			success: onSecaoSalvo.bind(this)
		});
		
	}

	function onErroSalvandoSecao(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span class="alert alert-danger">' + mensagemErro + '</span>');
		this.form.find('.form-row').addClass('has-error');
	}

	function onSecaoSalvo(secao) {
		var comboSecao = $('#secao');
		comboSecao.append('<option value=' + secao.codigo + '>' + secao.nome + '</option>');
		comboSecao.val(secao.codigo);
		this.modal.modal('hide');
	}

	return SecaoCadastroRapido;

}());

$(function() {

	var secaoCadastroRapido = new Controlestoque.SecaoCadastroRapido();
	secaoCadastroRapido.iniciar();

});