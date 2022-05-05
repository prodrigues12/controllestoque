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
						'#4876FF',
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
		$.ajax({
			url: 'produto/produtosTopFive',
			method: 'GET',
			success: onInformacaoRecebida.bind(this)
		});
	}

	function onInformacaoRecebida(produtoTop) {

		var nomes = [];
		var quantidades = [];

		produtoTop.forEach(function(obj) {
			nomes.unshift(obj.nome);
			quantidades.unshift(obj.quantidade)
		});

//		var graficoRosca = new Chart(this.ctx, {
//			type: 'doughnut',
//			data: {
//				labels: [nomes],
//				//					'Red',
//				//					'Blue',
//				//					'Yellow',
//				//					'Greew',
//				//					'Violet',
//				//					
//				//				],
//				datasets: [{
//					label: 'TOP 5 CD-994',
//					data: [quantidades],
//					backgroundColor: [
//						'rgb(255, 99, 71)',
//						'rgb(54, 162, 235)',
//						'rgb(255 255 0)',
//						'rgb(50 205 50)',
//						'rgb(208, 32, 144)'
//					],
//					hoverOffset: 4
//				}]
//			}
//
//		});

var grafico = new Chart(this.ctx, {
			type: 'bar',
			data: {
				labels: nomes,
				datasets: [{
					data: quantidades,
					label: 'Pedidos ',

					backgroundColor: [
						'#6A5ACD',
						'#4876FF',
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
	return GraficosTopProdutos;

}());




$(function() {

	var graficoPedidoMes = new Controlestoque.GraficosPedidosMes();
	graficoPedidoMes.iniciar();

	var graficosTopProdutos = new Controlestoque.GraficosTopProdutos();
	graficosTopProdutos.iniciar();
});