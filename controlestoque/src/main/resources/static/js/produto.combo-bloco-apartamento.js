var Controllestoque = Controllestoque || {};


Controllestoque.ComboBloco = (function() {
	
	function ComboBloco() {
		this.combo = $('#bloco');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);

	}

	ComboBloco.prototype.iniciar = function() {
		this.combo.on('change', onBlocoAlterado.bind(this));

	}

	function onBlocoAlterado() {
		this.emitter.trigger('alterado', this.combo.val())
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
		this.comboBloco.on('alterado', onBlocoAlterado.bind(this));
		var codigoBloco = this.comboBloco.combo.val();
		inicializarApartamentos.call(this, codigoBloco);
	}

	function onBlocoAlterado(evento, codigoBloco) {
		
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
		bloco.forEach(function(apartamento) {
			options.push('<option value="' + apartamento.codigo + '">' + apartamento.nome + '</option>');
		});

		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');

		var codigoApartamentosSelecionado = this.inputHiddenApartamentosSelecionado.val();
		if (codigoApartamentosSelecionado) {
			this.combo.val(codigoApartamentosSelecionado);
		}
	}

	function reset() {
		this.combo.html('<option class="form-control" value="">Selecione um Apartamento </option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
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

	var comboBloco = new Controllestoque.ComboBloco();
	comboBloco.iniciar();
	
	var comboApartamento = new Controllestoque.ComboApartamento(comboBloco);
	comboApartamento.iniciar();

