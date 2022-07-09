var Controllestoque = Controllestoque || {};

var alertList = document.querySelectorAll('.alert-success')
alertList.forEach(function(alert) {

	swal({
		title: "Salvo com sucesso!",
		text: "Deseja adicionar outro objeto?",
		icon: "success",
		buttons: [true,"Sim" ],
		inforMode: ["Não", true]
		
	})
		.then((willDelete) => {
			if (willDelete) {
				location.reload();
			}
			else {
				var urlAtual = window.location.href;
				var urlNova = urlAtual.replace('/novo', '');
		window.location.href = urlNova;

			}

			new bootstrap.Alert(alert)
		})



})

