package com.computacion.taller.Modelo;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Consulta {
	
	@NotBlank(message  = "Ingrese una fecha válida")
	private String date;
	

	public Consulta() {
		
	}

	public Consulta(String date) {
	
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

}
