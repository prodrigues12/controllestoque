

alertList = document.querySelectorAll('.alert-success')




alertList.forEach(function(alert) {

	swal({
		title: "Operação realizada com sucesso!",
		icon: "success",
		button: true,
		timer: 5000,
	})

	new bootstrap.Alert(alert)


});

