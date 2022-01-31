var Controllestoque = Controllestoque || {};

Controllestoque.PesquisaRapidaColaborador = (function() {
	
	function PesquisaRapidaColaborador() {
		this.pesquisaRapidaColaboradorModal = $('#colaboradorModal');
		this.nomeInput = $('#nomeColaboradorModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-colaborador-btn'); 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaColaborador');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-colaborador').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaColaborador.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaColaboradorModal.on('shown.bs.modal', onModalShow.bind(this));
	}
	
	function onModalShow(){
		this.nomeInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaColaboradorModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			}, 
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		 var tabelaColaboradorPesquisaRapida = new Controllestoque.TabelaColaboradorPesquisaRapida();
		 tabelaColaboradorPesquisaRapida.iniciar();
	} 
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaColaborador;
	
}());

Controllestoque.TabelaColaboradorPesquisaRapida = (function() {
	
	function TabelaColaboradorPesquisaRapida(modalColaborador) {
		this.modalColaborador = modalColaborador;
		this.cliente = $('.js-colaborador-pesquisa-rapida');
	}
	
	TabelaColaboradorPesquisaRapida.prototype.iniciar = function() {
		this.cliente.on('click', onColaboradorSelecionado.bind(this));
	}
	
	function onColaboradorSelecionado(evento) {
	

		
		var colaboradorSelecionado = $(evento.currentTarget);
		$('#nomeColaborador').val(colaboradorSelecionado.data('nome'));
		$('#codigoColaborador').val(colaboradorSelecionado.data('codigo'));
		
				modalColaborador.modal('hide');
	}
	
	return TabelaColaboradorPesquisaRapida;
	
}());

$(function() {
	var pesquisaRapidaColaborador = new Controllestoque.PesquisaRapidaColaborador();
	pesquisaRapidaColaborador.iniciar();
	
});