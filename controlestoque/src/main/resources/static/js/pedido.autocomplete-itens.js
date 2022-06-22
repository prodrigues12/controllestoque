var Controllestoque = Controllestoque || {};

Controllestoque.Autocomplete = (function() {

	function Autocomplete() {
		this.nomeInput = $('.js-produto-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-produto').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);

	}

	Autocomplete.prototype.iniciar = function() {

		var options = {
			url: function(codigoOuNome) {
				return this.nomeInput.data('url') +'/autocomplete/'+ '?codigoOuNome=' + codigoOuNome;
			}.bind(this),
			getValue: 'nome',
			requestDelay: 400,
			ajaxSettings: {
				contentType: 'application/json'
			},

			list: {
				match: {
					enabled: true
				},
				maxNumberOfElements: 6,

				showAnimation: {
					type: "slide",
					time: 400
				},
				hideAnimation: {
					type: "slide",
					time: 400
				},
				onChooseEvent: onItemSelecionado.bind(this)
			}

		};
		this.nomeInput.easyAutocomplete(options);
	}

	function onItemSelecionado() {
console.log('Chegou no itemSelecionado, item:', this.nomeInput.getSelectedItemData());
		this.emitter.trigger('item-selecionado', this.nomeInput.getSelectedItemData());
		this.nomeInput.val('');
		this.nomeInput.focus();

	}

	return Autocomplete;

}());


