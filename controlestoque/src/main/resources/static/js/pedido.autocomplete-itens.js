var Controllestoque = Controllestoque || {};

Controllestoque.Autocomplete = (function() {

	function Autocomplete() {
		this.codigoOuNomeInput = $('.js-produto-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-produto').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);

	}

	Autocomplete.prototype.iniciar = function() {
		
		var options = {
			url: function(codigoOuNome) {
				return this.codigoOuNomeInput.data('url') + '?codigoOuNome=' + codigoOuNome;
			}.bind(this),
			getValue: 'nome',
//			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},

			template: {
				type: 'custom',
				method: template.bind(this)
			},
			list: {
				onChooseEvent: onItemSelecionado.bind(this)
			}

		};

		this.codigoOuNomeInput.easyAutocomplete(options);
	}

	function onItemSelecionado() {

		this.emitter.trigger('item-selecionado', this.codigoOuNomeInput.getSelectedItemData());
		this.codigoOuNomeInput.val('');
		this.codigoOuNomeInput.focus();

	}

	function template(nome, produto) {
		return this.template(produto);
	}

	return Autocomplete;

}());



