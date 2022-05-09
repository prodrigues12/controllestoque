var Controllestoque = Controllestoque || {};


Controllestoque.MaskMoney = (function() {

	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}

	MaskMoney.prototype.enable = function() {
		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
		this.plain.maskMoney({ precision: 0, thousands: '.' });
	}

	return MaskMoney;

}());

Controllestoque.DataFormat = (function() {

	function DataFormat() {
		this.inputDate = $('.js-data');

	}

	DataFormat.prototype.enable = function() {
	
this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	
	
		console.log(data); // retorna: 30/12/2020
	}

	return DataFormat;

}());


$(function() {

	var maskMoney = new Controllestoque.MaskMoney();
	maskMoney.enable();


	var dataFormat = new Controllestoque.DataFormat();
	dataFormat.enable();
}

)