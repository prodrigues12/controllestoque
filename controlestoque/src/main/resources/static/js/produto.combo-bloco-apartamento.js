var Controllestoque = Controllestoque || {};


Controllestoque.ComboBloco = (function() {
	
	function ComboBloco() {
		this.combo = $('#bloco');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);

	}

	ComboBloco.prototype.iniciar = function() {
		this.combo.on('change', onBlocoTrocado.bind(this));

	}

	function onBlocoTrocado() {
		this.emitter.trigger('trocado', this.combo.val());
	}

	return ComboBloco1;


}());

Controllestoque.ComboApartamento = (function() {

	function ComboApartamento(comboBloco) {
		this.comboBloco = comboBloco;
		this.combo = $('#apartamento');
		this.imgLoading = $('.js-img-loading-apartamento');
		this.inputHiddenApartamentoSelecionado = $('#inputHiddenApartamentoSelecionado')
	}
	ComboApartamento.prototype.iniciar = function() {
		reset.call(this);
		this.comboBloco.on('trocado', onBlocoTrocado.bind(this));
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
			options.push('<option class="form-control" value="' + apartamento.codigo + '">' + apartamento.nome + '</option>');
		});

		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');

		var codigoApartamentosSelecionado = this.inputHiddenApartamentoSelecionado.val();
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
	comboBloco1.iniciar();
	
	var comboApartamento = new Controllestoque.ComboApartamento(comboBloco);
	comboApartamento.iniciar();

