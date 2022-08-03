var Controllestoque = Controllestoque || {};

Controllestoque.Msg = (function() {

	function Msg() {
	
this.alertList = document.querySelectorAll('.alert-success')

this.botao = $('.js-submit-btn')
}


	Msg.prototype.iniciar = function() {
		this.botao.on('click', botao.bind(this));
	}
	
	function botao(evento) {
		evento.preventDefault();
		
		var botaoClicado = $(evento.target);
		var acao = botaoClicado.data('acao');
		
		if(acao ==='cancelado'){
			alertList.forEach(function(alert) {

	swal({
		title: "Operação realizada com sucesso!",
		text:'Alterado para cancelado',
		icon: "success",
		button: true,
		timer: 5000,
	})

	new bootstrap.Alert(alert)

	//		console.log(botao);
	
});
			
		}
		
		console.log(acao);
	
		acaoInput.attr('name', acao);
	}
	
	
//alertList.forEach(function(alert) {
//
//	swal({
//		title: "Operação realizada com sucesso!",
//		icon: "success",
//		button: true,
//		timer: 5000,
//	})
//
//	new bootstrap.Alert(alert)
//
//	//		console.log(botao);
//	
//});

return Msg
	
}());

$(function() {
	
	var msg = new Controllestoque.Msg();
	msg.iniciar();
});