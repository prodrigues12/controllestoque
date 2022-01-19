var Controllestoque = Controllestoque || {};

Controllestoque.ComboGrupo = (function() {

	function ComboGrupo() {
		this.combo = $('#grupos');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}

	ComboGrupo.prototype.iniciar = function() {
		this.combo.on('change', onGrupoAlterado.bind(this));
	}

	function onGrupoAlterado() {
		this.emitter.trigger('alterado', this.combo.val());
	}

	return ComboGrupo;

}());

Controllestoque.ComboSubgrupo = (function() {

	function ComboSubgrupo(comboGrupo) {
		this.comboGrupo = comboGrupo;
		this.combo = $('#subgrupo');
		this.imgLoading = $('.js-img-loading');
		this.inputHiddenSubgrupoSelecionado = $('#inputHiddenSubgrupoSelecionado');
	}

	ComboSubgrupo.prototype.iniciar = function() {
		reset.call(this);
		this.comboGrupo.on('alterado', onGrupoAlterado.bind(this));
		var codigoGrupo = this.comboGrupo.combo.val();
		inicializarSubgrupos.call(this, codigoGrupo);
	}

	function onGrupoAlterado(evento, codigoGrupo) {
		this.inputHiddenSubgrupoSelecionado.val('');
		inicializarSubgrupos.call(this, codigoGrupo);
	}

	function inicializarSubgrupos(codigoGrupo) {
		if (codigoGrupo) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'grupo': codigoGrupo },
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarSubgruposFinalizado.bind(this));
		} else {
			reset.call(this);
		}
	}

	function onBuscarSubgruposFinalizado(subgrupos) {
		var options = [];
		subgrupos.forEach(function(subgrupo) {
			options.push('<option value="' + subgrupo.codigo + '">' + subgrupo.nome + '</option>');
		});

		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');

		var codigoSubgrupoSelecionado = this.inputHiddenSubgrupoSelecionado.val();
		if (codigoSubgrupoSelecionado) {
			this.combo.val(codigoSubgrupoSelecionado);
		}
	}

	function reset() {
		this.combo.html('<option value="">Selecione o Subgrupo</option>');
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

	return ComboSubgrupo;

}());

$(function() {

	var comboGrupo = new Controllestoque.ComboGrupo();
	comboGrupo.iniciar();

	var comboSubgrupo = new Controllestoque.ComboSubgrupo(comboGrupo);
	comboSubgrupo.iniciar();

});
