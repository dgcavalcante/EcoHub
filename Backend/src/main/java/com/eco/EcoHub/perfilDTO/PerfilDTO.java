package com.eco.EcoHub.perfilDTO;

import jakarta.validation.constraints.NotBlank;

public class PerfilDTO {
	@NotBlank(message = "O nome do perfil é obrigatório.")
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
