var Controllestoque = Controllestoque || {};

Controllestoque.MascaraCpfCnpjId = (function() {
	
	function MascaraCpfCnpjId() {
		this.radioTipoColaborador = $('.js-radio-tipo-colaborador');
		this.labelCpfCnpjId = $('[for=cpfCnpjId]');
		this.inputCpfCnpjId = $('#cpfCnpjId');
	}
	
	MascaraCpfCnpjId.prototype.iniciar = function() {
		this.radioTipoColaborador.on('change', onTipoColaboradorAlterado.bind(this));
		var tipoColaboradorSelecionado = this.radioTipoColaborador.filter(':checked')[0];
		if(tipoColaboradorSelecionado){
			aplicarMascara.call(this, $(tipoColaboradorSelecionado));
		}
	}
	
	function onTipoColaboradorAlterado(evento) {
		var tipoColaboradorSelecionado = $(evento.currentTarget);
		aplicarMascara.call(this,tipoColaboradorSelecionado);
		this.inputCpfCnpjId.val('');
		
	}
	
	function aplicarMascara(tipoColaboradorSelecionado){
		this.labelCpfCnpjId.text(tipoColaboradorSelecionado.data('documento'));
		this.inputCpfCnpjId.mask(tipoColaboradorSelecionado.data('mascara'));
		this.inputCpfCnpjId.removeAttr('disabled');
		
	}
	
	return MascaraCpfCnpjId;
	
}());

$(function() {
	var mascaraCpfCnpjId = new Controllestoque.MascaraCpfCnpjId();
	mascaraCpfCnpjId.iniciar();
});