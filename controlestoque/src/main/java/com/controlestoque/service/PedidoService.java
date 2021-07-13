package com.controlestoque.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PedidoService {

//	##Pegar data e hora no atual
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
