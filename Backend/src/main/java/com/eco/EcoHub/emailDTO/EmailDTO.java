package com.eco.EcoHub.emailDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailDTO {
	@Email(message = "E-mail inválido")
	@NotBlank(message = "O e-mail é obrigatório")
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
