window.onload = function () {

	validarForm();

};

function validarForm() {



	if ($('#value_1').val().length > 4) {
		$('#value_2').removeAttr('disabled')
		$('#pedidoBuscar').removeAttr('disabled')
	}
	else {
		$('#pedidoBuscar').attr('disabled', 'disabled')
	}
}

$('#value_1, #value_2').on('focusout', validarForm)

$('#value_1, #value_2').keyup(validarForm)

