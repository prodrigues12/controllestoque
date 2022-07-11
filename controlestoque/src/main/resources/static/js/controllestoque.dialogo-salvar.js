var Controllestoque = Controllestoque || {};

var alertList = document.querySelectorAll('.alert-success')
alertList.forEach(function(alert) {

	swal({
		title: "Salvo com sucesso!",
		icon: "success",
		text: "O que deseja fazer?",
		buttons: {

			cancel: "Voltar",
			catch: {
				text: "Nova Seção",
				value: "catch",
			},

		},
		timer: 10000,
	})
		.then((value) => {
			switch (value) {
				case "catch":
					break;

				default:
					var urlAtual = window.location.href;
					var urlNova = urlAtual.replace('/novo', '');
					window.location.href = urlNova;
			}
			new bootstrap.Alert(alert)
		});
})
