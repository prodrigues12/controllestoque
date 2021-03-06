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
						'#90EE90',
						'#FF6347',
						'#F0E68C',	
						'#9370DB',
						'#D3D3D3',
						'#F4A460'
					],
					borderColor: '#000',
					pointBorderColor: '#000',
					pointBackgroundColor: "#000",
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

		var graficoRosca = new Chart(this.ctx, {
			type: 'doughnut',
			data: {
				labels: nomes,
				
				datasets: [{
					data: quantidades,
					backgroundColor: [
						'#DC143C',
						'#90EE90',
						'#20B2AA',
						'#FFA500',
//						'#A020F0',
						'#2F4F4F'
					],
					hoverOffset: 10
				}],
				
			},
			options:{
				aspectRatio:2,
				 plugins: {
            subtitle: {
                display: true,
                text: 'Nos últimos 6 meses'
            },
            legend:{
	position:'right'
}
        }
				
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