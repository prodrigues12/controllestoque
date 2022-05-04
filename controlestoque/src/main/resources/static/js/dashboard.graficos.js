var Controlestoque = Controlestoque || {};

Controlestoque.GraficosPedidosMes = (function() {

	function GraficoPedidoMes() {
		this.ctx = $('#graficoVendasPorMes');

	}

	GraficoPedidoMes.prototype.iniciar = function() {

		$.ajax({
			url: 'pedido/totalPorMes',
			method: 'GET',
			success: onDadosRecebidos.bind(this)
		});
	}

	function onDadosRecebidos(pedidoMes) {

		var meses = [];
		var valor = [];

		pedidoMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valor.unshift(obj.total)
		});

		var grafico = new Chart(this.ctx, {
			type: 'bar',
			data: {
				labels: meses,
				datasets: [{
					data: valor,
					label: 'Pedidos ',

					backgroundColor: [
						'#6A5ACD',
						'#FF00FF',
						'#FFA500',
						'#32CD32',
						'#A020F0',
						'#FF7F50'
					],
					borderColor: '#fff',
					pointBorderColor: '#fff',
					pointBackgroundColor: "#fff",
				}]
			},
		});

	}

	return GraficoPedidoMes;

}());

Controlestoque.GraficosTopProdutos = (function() {

	function GraficosTopProdutos() {
		this.ctx = $('#graficosTopProdutos');

	}

	GraficosTopProdutos.prototype.iniciar = function() {

		var grafico = new Chart(this.ctx, {
			type: 'pie',
			data: {
				labels: [
					'Red',
					'Blue',
					'Yellow'
				],
				datasets: [{
					label: 'My First Dataset',
					data: [300, 50, 100],
					backgroundColor: [
						'rgb(255, 99, 132)',
						'rgb(54, 162, 235)',
						'rgb(255, 205, 86)'
					],
					hoverOffset: 4
				}]
			}
			
		});
}
	return GraficosTopProdutos;

}());




$(function() {

	var graficoPedidoMes = new Controlestoque.GraficosPedidosMes();
	graficoPedidoMes.iniciar();

	var graficosTopProdutos = new Controlestoque.GraficosTopProdutos();
	graficosTopProdutos.iniciar();
});