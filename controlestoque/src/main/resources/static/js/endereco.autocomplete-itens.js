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
//				onChooseEvent: onItemSelecionado.bind(this)
			}

		};

		this.codigoOuNomeInput.easyAutocomplete(options);
	}

	function onItemSelecionado() {

		this.emitter.trigger('item-selecionado', this.codigoOuNomeInput.getSelectedItemData());
		this.codigoOuNomeInput.val('');
		this.codigoOuNomeInput.focus();

	}

	return Autocomplete;

}());


$(function() {

	var autocomplete = new Controllestoque.Autocomplete();
	autocomplete.iniciar();


})


