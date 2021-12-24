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
		this.emitter.trigger('alterado', this.combo.val())
	}

	return ComboGrupo;

}());

Controllestoque.ComboSubGrupo = (function() {

	function ComboSubGrupo(comboGrupo) {
		this.comboGrupo = comboGrupo;
		this.combo = $('#subgrupo');
		this.imgLoading = $('.js-img-loading-sub-grupo');
		this.inputHiddenSubGrupoSelecionado = $('#inputHiddenSubGrupoSelecionado')
	}
	
	ComboSubGrupo.prototype.iniciar = function() {
		reset.call(this);
		this.comboGrupo.on('alterado', onGrupoAlterado.bind(this));
		var codigoGrupo = this.comboGrupo.combo.val();
		inicializarSubGrupos.call(this, codigoGrupo);
	}

	function onGrupoAlterado(evento, codigoGrupo) {
		
		this.inputHiddenSubGrupoSelecionado.val('');
		inicializarSubGrupos.call(this, codigoGrupo);
	}

	function inicializarSubGrupos(codigoGrupo) {
		if (codigoGrupo) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'grupo': codigoGrupo },
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarSubGruposFinalizado.bind(this));
		} else {
			reset.call(this);
		}
	}

	function onBuscarSubGruposFinalizado(subgrupos) {
		var options = [];
		subgrupos.forEach(function(subgrupo) {
			options.push('<option class="form-control" value="' + subgrupo.codigo + '">' + subgrupo.nome + '</option>');
		});

		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');

		var codigoSubGrupoSelecionado = this.inputHiddenSubGrupoSelecionado.val();
		if (codigoSubGrupoSelecionado) {
			this.combo.val(codigoSubGrupoSelecionado);
		}
	}

	function reset() {
		this.combo.html('<option value="">Selecione o sub-grupo </option>');
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

	return ComboSubGrupo;

}());



$(function() {

	var comboGrupo = new Controllestoque.ComboGrupo();
	comboGrupo.iniciar();

	var comboSubGrupo = new Controllestoque.ComboSubGrupo(comboGrupo);
	comboSubGrupo.iniciar();
});