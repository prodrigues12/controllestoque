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



	return GraficoPedidoMes;

}());

$(function() {

	var graficoPedidoMes = new Controlestoque.GraficosPedidosMes();
	graficoPedidoMes.iniciar();
});