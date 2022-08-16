

	function validarLogin() {
    if ($('#username').val().length > 0 && $('#password').val().length > 0) {
	

	
        $('#kc-login').removeAttr('disabled')
    } else {
        $('#kc-login').attr('disabled', 'disabled')
    }
}

$('#username, #password').on('focusout', validarLogin)

$('#username, #password').keyup(validarLogin)
	
	





