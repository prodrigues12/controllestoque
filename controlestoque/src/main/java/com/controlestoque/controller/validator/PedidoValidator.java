package com.controlestoque.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.controlestoque.model.Pedido;

@Component
public class PedidoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Pedido.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "colaborador.codigo", "", "Informe o colaborador clicando na lupa");
		
		Pedido pedido = (Pedido) target;
		validarSeInformouTurno(errors,pedido);
		validarSeInformouItens(errors, pedido);
		
	}

	private void validarSeInformouItens(Errors errors, Pedido pedido) {
		System.out.println(pedido.getItens().size());
		if(pedido.getItens().isEmpty()) {
			errors.reject("", "Adicione pelo menos um item no pedido");
		}
	}

	private void validarSeInformouTurno(Errors errors, Pedido pedido) {
		if(pedido.getTurno() == null) {
			errors.rejectValue("turno", "", "Campo turno é obrigatorio");
		}
		
	}

}
