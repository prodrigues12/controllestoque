var Controlestoque = Controlestoque || {};

Brewer.EstiloCadastroRapido = (function() {

	//construtor
	//Objeto
//	function EstiloCadastroRapido() {
//		//inicializações
//		this.modal = $('#modalCadastroEstilo');
//		this.botaoSalvar = this.modal.find('.js-modal-cadastro-estilo-salvar-btn');
//		this.form = this.modal.find('form');
//		this.url = this.form.attr('action');
//		this.inputNomeEstilo = $('#nomeEstilo');
//		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
//	}
//
//	//prototype
//	EstiloCadastroRapido.prototype.iniciar = function() {
//		this.form.on('submit', function(event) { event.preventDefault() });// enter no form não submit
//		this.modal.on('shown.bs.modal', onModalShow.bind(this));
//		this.modal.on('hide.bs.modal', onModalClose.bind(this));
//		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
//	}
//
//	function onModalShow() {
//		this.inputNomeEstilo.focus();
//	}
//
//	function onModalClose() {
//		this.inputNomeEstilo.val('');
//		this.containerMensagemErro.addClass('');
//		this.form.find('.form-group').removeClass('has-error');
//	}
//
//	function onBotaoSalvarClick() {
//		var nomeEstilo = this.inputNomeEstilo.val().trim();  
//		$.ajax({
//			url: this.url,
//			method: 'POST',
//			contentType: 'application/json',
//			data: JSON.stringify({ nome: nomeEstilo }),
//			error: onErroSalvandoEstilo.bind(this),
//			success: onEstiloSalvo.bind(this)
//		});
//		
//	}
//
//	function onErroSalvandoEstilo(obj) {
//		var mensagemErro = obj.responseText;
//		this.containerMensagemErro.removeClass('hidden');
//		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
//		this.form.find('.form-group').addClass('has-error');
//	}
//
//	function onEstiloSalvo(estilo) {
//		var comboEstilo = $('#estilo');
//		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
//		comboEstilo.val(estilo.codigo);
//		this.modal.modal('hide');
//	}
//
//	return EstiloCadastroRapido;
//
//}());
//
//
//
//$(function() {
//	// criar objeto e iniciar 
//	var estiloCadastroRapido = new Brewer.EstiloCadastroRapido();
//	estiloCadastroRapido.iniciar();
//
});