var Controlestoque = Controlestoque || {};

Controlestoque.GraficosPedidosMes = (function() {

	function GraficoPedidoMes() {
		this.ctx = $('#graficoVendasPorMes');

	}

	GraficoPedidoMes.prototype.iniciar = function() {
		
		$.ajax({
			url:'pedido/totalPorMes',
			method:'GET',
			success: onDadosRecebidos.bind(this)
		});
		}
		
		function onDadosRecebidos (pedidoMes){

		var meses = [];
		var valor = [];
		
		pedidoMes.forEach(function(obj){
			meses.unshift(obj.mes);
			valor.unshift(obj.total)
		});
		
		var grafico = new Chart(this.ctx, {
			type: 'line',
			data: {
				labels: meses,
				datasets: [{
					backgroundColor: 'rgb(123, 12, 132)',
					label: 'Pedidos ',
					borderColor: 'rgb(75, 192, 192)',
					data: valor,


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