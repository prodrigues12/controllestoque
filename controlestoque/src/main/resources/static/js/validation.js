(function() {
	
	var forms = document.querySelectorAll('.needs-validation')
	Array.prototype.slice.call(forms)
		.forEach(function(form) {
			form.addEventListener('submit', function(event) {
				if (!form.checkValidity()) {
					event.preventDefault()
					event.stopPropagation()
				}
				form.classList.add('was-validated')
			}, false)
		})
})()

function validarPerido(data, id) {

	$(function() {
		var dataInicio = getDate(data)
		var month = dataInicio.getMonth() + 1;
		var day = dataInicio.getDate();
		var year = dataInicio.getFullYear();
		if (month < 10)
			month = '0' + month.toString();
		if (day < 10)
			day = '0' + day.toString();
		var maxDate = year + '-' + month + '-' + day;
		$('#' + id).attr('min', maxDate);
	});

}

function getDate(value) {
	if (!value) { return new Date(); }
	if (value.lenght < 10) { return new Date(); }
	try {
		const dataParts = value.split('-');
		const ano = dataParts[0]; const mes = dataParts[1]; const dia = dataParts[2];
		return new Date(ano, mes - 1, dia);
	} catch (e) { return new Date(); }
}


function validarForm() {
	
    if ($('#dataInicio').val().length > 0 ) {
        $('#dataFim').removeAttr('disabled')
    } 
      if ($('#dataFim').val().length > 0 ) {
	
        $('#btn-salvar').removeAttr('disabled')
    } else {
        $('#btn-salvar').attr('disabled', 'disabled')
    }
}

$('#dataInicio, #dataFim').on('focusout', validarForm)

$('#dataInicio, #dataFim').keyup(validarForm)
	














