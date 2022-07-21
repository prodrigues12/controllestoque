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




$(function() {

	var maskMoney = new Controllestoque.MaskMoney();
	maskMoney.enable();


}

)