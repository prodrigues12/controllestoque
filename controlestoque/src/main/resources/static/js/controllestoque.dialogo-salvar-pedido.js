var Controllestoque = Controllestoque || {};

var alertList = document.querySelectorAll('.alert-success')
alertList.forEach(function(alert) {

	swal({
		title: "Pedido realizado com sucesso!",
		icon: "success",
		button: true,

		timer: 5000,
	})
		
			
//			new bootstrap.Alert(alert)
		
});

