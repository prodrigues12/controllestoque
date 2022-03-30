var Controlestoque = Controlestoque || {};

Controlestoque.GraficosPedidosMes = (function() {

	function GraficoPedidoMes() {
		this.ctx = $('#graficoVendasPorMes');
		
	}

	GraficoPedidoMes.prototype.iniciar = function() {

		var grafico = new Chart(this.ctx, {
			type: 'line',
			data: {
				labels: ['dez','jan', 'fev', 'mar','abr','mai'],
				datasets: [{
					backgroundColor: 'rgb(123, 12, 132)',
					label: 'My First ',
					borderColor: 'rgb(75, 192, 192)',
					data: [6,2 ,9, 7, 3, 5],
					

				}]
			},
		});

	}
	/*
	GraficoPedidoMes.prototype.iniciar = function() {

	$.ajax({
		url='pedido/totalPorMes',
		method='GET',
		success: onDadosRecebidos.bind(this)
	});
	}

	function onDadosRecebidos(pedidoMes) {

		var mes = [];
		var total = [];
		pedidoMes.forEach(function(obj){
			mes.unshift(obj.mes);
			mes.unshift(obj.total);
		});
		
		var grafico = new Chart(this.ctx, {
			type: 'line',
			data: {
				labels: mes,
				datasets: [{
					backgroundColor: 'rgb(123, 12, 132)',
					label: 'My First ',
					borderColor: 'rgb(75, 192, 192)',
					data: total,


				}]
			},
		});
	}
*/



	return GraficoPedidoMes;

}());

$(function() {

	var graficoPedidoMes = new Controlestoque.GraficosPedidosMes();
	graficoPedidoMes.iniciar();
});