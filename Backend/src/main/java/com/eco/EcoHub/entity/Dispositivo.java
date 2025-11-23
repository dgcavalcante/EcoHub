package com.eco.EcoHub.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Dispositivos")
public class Dispositivo implements Serializable {
	public Dispositivo() {
		this.statusDispositivo = true;
		this.dataCriacao = ZonedDateTime.now(FUSO_BR);
		this.dataUltimoCalculo = this.dataCriacao;
		this.setConsumoTotal(BigDecimal.ZERO);
		this.setEmUmComodo(false);
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idDispositivo;
	
	private String nome;

	private String tipo;
	
	private BigDecimal consumoPorHora;

	private boolean statusDispositivo;
	
	private final ZoneId FUSO_BR = ZoneId.of("America/Sao_Paulo");
	
	private ZonedDateTime dataCriacao;
	private ZonedDateTime dataUltimoCalculo;
	
	private BigDecimal consumoTotal;
	private boolean emUmComodo;
	
	private void calcularConsumoEnergia() {
		if(statusDispositivo) {
			ZonedDateTime agora = ZonedDateTime.now(FUSO_BR);
			long horasDecorridas = Duration.between(dataUltimoCalculo, agora).toHours();
			setConsumoTotal(consumoPorHora.multiply(BigDecimal.valueOf(horasDecorridas)));
			dataUltimoCalculo = agora;
		}
	}
	// metodo para atualizar o consumo de energia e retornar o valor atualizado no endpoint que vou criar
	public BigDecimal atualizarConsumoEnergia() {
		calcularConsumoEnergia();
		return getConsumoTotal();
	}
	public void ligarDispositivo() {
		calcularConsumoEnergia();
		this.statusDispositivo = true;
	}
	public void desligarDispositivo() {
		calcularConsumoEnergia();
		this.statusDispositivo = false;
	}

	public UUID getIdDispositivo() {
		return this.idDispositivo;
	}

	public void setIdDispositivo(UUID idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public String getNomeDispositivo() {
		return this.nome;
	}

	public void setNomeDispositivo(String nomeDispositivo) {
		this.nome = nomeDispositivo;
	}

	public String getTipoDispositivo() {
		return this.tipo;
	}
	
	public BigDecimal getConsumoEnergetico() {
		return consumoPorHora;
	}
	
	public void setConsumoEnergetico(BigDecimal consumoEnergetico) {
		this.consumoPorHora = consumoEnergetico;
	}

	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipo = tipoDispositivo;
	}

	public boolean getStatusDispositivo() {
		return this.statusDispositivo;
	}

	public void setStatusDispositivo(boolean statusDispositivo) {
		this.statusDispositivo = statusDispositivo;
	}

	public BigDecimal getConsumoTotal() {
		return consumoTotal;
	}

	public void setConsumoTotal(BigDecimal consumoTotal) {
		this.consumoTotal = consumoTotal;
	}
	public boolean isEmUmComodo() {
		return emUmComodo;
	}
	public void setEmUmComodo(boolean emUmComodo) {
		this.emUmComodo = emUmComodo;
	}

}
