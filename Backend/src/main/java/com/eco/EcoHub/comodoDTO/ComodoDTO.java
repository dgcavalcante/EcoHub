package com.eco.EcoHub.comodoDTO;

import jakarta.validation.constraints.NotBlank;

public class ComodoDTO {
	@NotBlank(message = "O nome do cômodo é obrigatório")
	private String name;
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
