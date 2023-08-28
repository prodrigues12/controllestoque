
//$(document).ready(function() {
//	$('.js-idMagalu').mask('000000', { reverse: true })
//	
//	$('.js-cnpj').mask('00000000000000', { reverse: true })
//	
//	$('.js-numero').mask('000', { reverse: true })
//	
//});

jQuery(function(){
	jQuery('#valorCusto').maskMoney({
		thousands:'.',
		decimal:','
	})
	
});

