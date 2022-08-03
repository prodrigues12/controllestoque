var Controllestoque = Controllestoque || {};

var alertList = document.querySelectorAll('.alert-success')
alertList.forEach(function(alert) {

	swal({
		title: "Salvo com sucesso!",
		icon: "success",
		text: "O que deseja fazer?",
		buttons: {
			defeat: { text: "Ajustar Estoque" },
			cancel: "Voltar",
			catch: {
				text: "Novo produto",
				value: "catch",
			},

		},
		timer: 10000,
	})
		.then((value) => {
			
			var urlAtual = window.location.href;
			
			switch (value) {

				case "defeat":
					window.location.assign("/estoque")
					break;

				case "catch":
					break;

				default:
					
					var urlNova = urlAtual.replace('/novo', '');
					window.location.href = urlNova;
			}
			new bootstrap.Alert(alert)
		});
})

