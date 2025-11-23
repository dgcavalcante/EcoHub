package com.eco.EcoHub.DispositivoDTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public class DispositivoDTO {
	
	@NotBlank(message = "O nome do dispositivo é obrigatório.")
	private String nome;
	@NotBlank(message = "O tipo do dispositivo é obrigatório.")
	private String tipo;
	@NotBlank(message = "O consumo por hora do dispositivo é obrigatório.")
	private BigDecimal consumoPorHora;
	@NotBlank(message = "O status do dispositivo é obrigatório.")
	private boolean statusDispositivo;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getConsumoPorHora() {
		return consumoPorHora;
	}
	public void setConsumoPorHora(BigDecimal consumoPorHora) {
		this.consumoPorHora = consumoPorHora;
	}
	public boolean isStatusDispositivo() {
		return statusDispositivo;
	}
	public void setStatusDispositivo(boolean statusDispositivo) {
		this.statusDispositivo = statusDispositivo;
	}

}
