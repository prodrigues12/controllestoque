var Controllestoque = Controllestoque || {};


Controllestoque.ComboRua = (function() {

	function ComboRua() {
		this.combo = $('#rua');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);

	}

	ComboRua.prototype.iniciar = function() {
		this.combo.on('change', onRuaAlterada.bind(this));

	}

	function onRuaAlterada() {
		this.emitter.trigger('alterada', this.combo.val());
	}

	return ComboRua;


}());

Controllestoque.ComboBloco = (function() {
	
	this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);

	function ComboBloco(comboRua) {
		this.comboRua = comboRua;
		this.combo = $('#bloco');
		this.imgLoading = $('.js-img-loading-bloco');
		this.inputHiddenBlocoSelecionado = $('#inputHiddenBlocoSelecionado')
	}
	ComboBloco.prototype.iniciar = function() {
		reset.call(this);
		this.comboRua.on('alterada', onRuaAlterada.bind(this));
		var codigoRua = this.comboRua.combo.val();
		inicializarBlocos.call(this, codigoRua);
	}

	function onRuaAlterada(evento, codigoRua) {
		
		this.inputHiddenBlocoSelecionado.val('');
		inicializarBlocos.call(this, codigoRua);
	}

	function inicializarBlocos(codigoRua) {
		if (codigoRua) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'rua': codigoRua },
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarBlocosFinalizado.bind(this));
		} else {
			reset.call(this);
		}
	}

	function onBuscarBlocosFinalizado(bloco) {
		var options = [];
		bloco.forEach(function(bloco) {
			options.push('<option class="form-control" value="' + bloco.codigo + '">' + bloco.nome + '</option>');
		});

		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');

		var codigoBlocoSelecionado = this.inputHiddenBlocoSelecionado.val();
		if (codigoBlocoSelecionado) {
			this.combo.val(codigoBlocoSelecionado);
		}
	}

	function reset() {
		this.combo.html('<option value="">Selecione o Bloco </option>');
		this.combo.val('');
//		this.combo.attr('disabled', 'disabled');
	}

	function iniciarRequisicao() {
		reset.call(this);
		this.imgLoading.show();
	}

	function finalizarRequisicao() {
		this.imgLoading.hide();
	}

	return ComboBloco;

}());

Controllestoque.ComboApartamento = (function() {

	function ComboApartamento(comboBloco) {
		this.comboBloco = comboBloco;
		this.combo = $('#apartamento');
		this.imgLoading = $('.js-img-loading-apartamento');
		this.inputHiddenBlocoSelecionado = $('#inputHiddenApartamentoSelecionado')
	}
	ComboApartamento.prototype.iniciar = function() {
		reset.call(this);
		this.comboBloco.on('alterado', onBlocoTrocado.bind(this));
		var codigoBloco = this.comboBloco.combo.val();
		inicializarApartamentos.call(this, codigoBloco);
	}

	function onBlocoTrocado(evento, codigoBloco) {
		
		this.inputHiddenApartamentoSelecionado.val('');
		inicializarApartamentos.call(this, codigoBloco);
	}

	function inicializarApartamentos(codigoBloco) {
		if (codigoBloco) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'bloco': codigoBloco },
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarpartamentosFinalizado.bind(this));
		} else {
			reset.call(this);
		}
	}

	function onBuscarpartamentosFinalizado(apartamento) {
		var options = [];
		apartamento.forEach(function(apartamento) {
			options.push('<option value="' + apartamento.codigo + '">' + apartamento.nome + '</option>');
		});

		this.combo.html(options.join(''));
//		this.combo.removeAttr('disabled');

		var codigoApartamentosSelecionado = this.inputHiddenApartamentosSelecionado.val();
		if (codigoApartamentosSelecionado) {
			this.combo.val(codigoApartamentosSelecionado);
		}
	}

	function reset() {
		this.combo.html('<option class="form-control" value="">Selecione um Apartamento-js </option>');
		this.combo.val('');
//		this.combo.attr('disabled', 'disabled');
	}

	function iniciarRequisicao() {
		reset.call(this);
		this.imgLoading.show();
	}

	function finalizarRequisicao() {
		this.imgLoading.hide();
	}

	return ComboApartamento;

}());



$(function() {

	var comboRua = new Controllestoque.ComboRua();
	comboRua.iniciar();

	var comboBloco = new Controllestoque.ComboBloco(comboRua);
	comboBloco.iniciar();
	
//	var comboApartamento = new Controllestoque.ComboApartamento(comboBloco);
//	comboApartamento.iniciar();
});