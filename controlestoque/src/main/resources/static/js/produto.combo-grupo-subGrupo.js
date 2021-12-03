var Controllestoque = Controllestoque || {};


Controllestoque.ComboGrupo = (function() {

	function ComboGrupo() {
		this.combo = $('#grupo');
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

	function ComboSubGrupo(ComboGrupo)
	this.comboGrupo = comboGrupo;
	this.combo = $('#subGrupo');
	this.imgLoading = $('.js-img-loading');
	this.inputHiddenSubGrupoSelecionada = $('#inputHiddenSubGrupoSelecionada')

	ComboSubGrupo.prototype.iniciar = function() {
		reset.call(this);
		this.comboGrupo.on('alterado', onGrupoAlterado.bind(this));
		var codigoGrupo = this.comboGrupo.combo.val();
		inicializarSubGrupos.call(this, codigoEstado);
	}

	function onGrupoAlterado(evento, codigoGrupo) {
		this.inputHiddenSubGrupoSelecionada.val('');
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

	function onBuscarSubGruposFinalizado(subGrupo) {
		var options = [];
		subGrupos.forEach(function(subGrupo) {
			options.push('<option value="' + subGrupo.codigo + '">' + subGrupo.nome + '</option>');
		});

		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');

		var codigoSubGrupoSelecionada = this.inputHiddenSubGrupoSelecionada.val();
		if (codigoSubGrupoSelecionada) {
			this.combo.val(codigoSubGrupoSelecionada);
		}
	}

	function reset() {
		this.combo.html('<option value="">Selecione o subgrupo </option>');
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

	var comboSubGrupo = new Controllestoque.ComboSubGrupo();
	comboSubGrupo.iniciar();
})