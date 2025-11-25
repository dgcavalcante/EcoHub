package com.eco.EcoHub.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import jakarta.persistence.Column;
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
		this.setConsumoJaneiro(BigDecimal.ZERO);
		this.setConsumoFevereiro(BigDecimal.ZERO);
		this.setConsumoMarco(BigDecimal.ZERO);
		this.setConsumoAbril(BigDecimal.ZERO);
		this.setConsumoMaio(BigDecimal.ZERO);
		this.setConsumoJunho(BigDecimal.ZERO);
		this.setConsumoJulho(BigDecimal.ZERO);
		this.setConsumoAgosto(BigDecimal.ZERO);
		this.setConsumoSetembro(BigDecimal.ZERO);
		this.setConsumoOutubro(BigDecimal.ZERO);
		this.setConsumoNovembro(BigDecimal.ZERO);
		this.setConsumoDezembro(BigDecimal.ZERO);
		this.setEmUmComodo(false);
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idDispositivo;
	
	@Column(name = "nome_dispositivo")
	private String nome;
	
	@Column(name = "tipo_dispositivo")
	private String tipo;
	
	@Column(name = "consumo_por_hora")
	private BigDecimal consumoPorHora;
	
	@Column(name = "statusDispositivo")
	private boolean statusDispositivo;
	
	private final ZoneId FUSO_BR = ZoneId.of("America/Sao_Paulo");
	
	private ZonedDateTime dataCriacao;
	
	private ZonedDateTime dataUltimoCalculo;
	
	@Column(name = "consumo_total")
	private BigDecimal consumoTotal;
	
	@Column(name = "em_um_comodo")
	private boolean emUmComodo;

	@Column(name = "consumo_janeiro")
	private BigDecimal consumoJaneiro;
	
	@Column(name = "consumo_fevereiro")
	private BigDecimal consumoFevereiro;
	
	@Column(name = "consumo_marco")
	private BigDecimal consumoMarco;
	
	@Column(name = "consumo_abril")
	private BigDecimal consumoAbril;
	
	@Column(name = "consumo_maio")
	private BigDecimal consumoMaio;
	
	@Column(name = "consumo_junho")
	private BigDecimal consumoJunho;
	
	@Column(name = "consumo_julho")
	private BigDecimal consumoJulho;
	
	@Column(name = "consumo_agosto")
	private BigDecimal consumoAgosto;
	
	@Column(name = "consumo_setembro")
	private BigDecimal consumoSetembro;
	
	@Column(name = "consumo_outubro")
	private BigDecimal consumoOutubro;
	
	@Column(name = "consumo_novembro")
	private BigDecimal consumoNovembro;
	
	@Column(name = "consumo_dezembro")
	private BigDecimal consumoDezembro;
	
	public void injetarConsumoPorHora(int mes, int quantidadeDeHoras) {
		BigDecimal calculo = BigDecimal.ZERO;
		switch (mes) {
		case 1:
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoJaneiro(calculo);
			break;
		case 2:
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoFevereiro(calculo);
			break;
		case 3:
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoMarco(calculo);
			break;
		case 4: 
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoAbril(calculo);
			break;
		case 5: 
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoMaio(calculo);
			break;
		case 6:
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoJunho(calculo);
			break;
		case 7: 
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoJulho(calculo);
			break;
		case 8:
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoAgosto(calculo);
			break;
		case 9: 
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoSetembro(calculo);
			break;
		case 10: 
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoOutubro(calculo);
			break;
		case 11: 
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoNovembro(calculo);
			break;
		case 12: 
			calculo = consumoPorHora.multiply(BigDecimal.valueOf(quantidadeDeHoras));
			setConsumoDezembro(calculo);
			break;
		}
	}
	
	public Map<String, BigDecimal> relatorio() {
		Map<String, BigDecimal> relatorioTotal = new LinkedHashMap<>();
		relatorioTotal.put("Janeiro", getConsumoJaneiro());
		relatorioTotal.put("Fevereiro", getConsumoFevereiro());
		relatorioTotal.put("Março", getConsumoMarco());
		relatorioTotal.put("Abril", getConsumoAbril());
		relatorioTotal.put("Maio", getConsumoMaio());
		relatorioTotal.put("Junho", getConsumoJunho());
		relatorioTotal.put("Julho", getConsumoJulho());
		relatorioTotal.put("Agosto", getConsumoAgosto());
		relatorioTotal.put("Setembro", getConsumoSetembro());
		relatorioTotal.put("Outubro", getConsumoOutubro());
		relatorioTotal.put("Novembro", getConsumoNovembro());
		relatorioTotal.put("Dezembro", getConsumoDezembro());
		return relatorioTotal;
	}
	
	public BigDecimal getConsumoPorHora() {
		return consumoPorHora;
	}
	public void setConsumoPorHora(BigDecimal consumoPorHora) {
		this.consumoPorHora = consumoPorHora;
	}
	public BigDecimal getConsumoJaneiro() {
		return consumoJaneiro;
	}
	public void setConsumoJaneiro(BigDecimal consumoJaneiro) {
		this.consumoJaneiro = consumoJaneiro;
	}
	public BigDecimal getConsumoFevereiro() {
		return consumoFevereiro;
	}
	public void setConsumoFevereiro(BigDecimal consumoFevereiro) {
		this.consumoFevereiro = consumoFevereiro;
	}
	public BigDecimal getConsumoMarco() {
		return consumoMarco;
	}
	public void setConsumoMarco(BigDecimal consumoMarco) {
		this.consumoMarco = consumoMarco;
	}
	public BigDecimal getConsumoAbril() {
		return consumoAbril;
	}
	public void setConsumoAbril(BigDecimal consumoAbril) {
		this.consumoAbril = consumoAbril;
	}
	public BigDecimal getConsumoMaio() {
		return consumoMaio;
	}
	public void setConsumoMaio(BigDecimal consumoMaio) {
		this.consumoMaio = consumoMaio;
	}
	public BigDecimal getConsumoJunho() {
		return consumoJunho;
	}
	public void setConsumoJunho(BigDecimal consumoJunho) {
		this.consumoJunho = consumoJunho;
	}
	public BigDecimal getConsumoJulho() {
		return consumoJulho;
	}
	public void setConsumoJulho(BigDecimal consumoJulho) {
		this.consumoJulho = consumoJulho;
	}
	public BigDecimal getConsumoAgosto() {
		return consumoAgosto;
	}
	public void setConsumoAgosto(BigDecimal consumoAgosto) {
		this.consumoAgosto = consumoAgosto;
	}
	public BigDecimal getConsumoSetembro() {
		return consumoSetembro;
	}
	public void setConsumoSetembro(BigDecimal consumoSetembro) {
		this.consumoSetembro = consumoSetembro;
	}
	public BigDecimal getConsumoOutubro() {
		return consumoOutubro;
	}
	public void setConsumoOutubro(BigDecimal consumoOutubro) {
		this.consumoOutubro = consumoOutubro;
	}
	public BigDecimal getConsumoNovembro() {
		return consumoNovembro;
	}
	public void setConsumoNovembro(BigDecimal consumoNovembro) {
		this.consumoNovembro = consumoNovembro;
	}
	public BigDecimal getConsumoDezembro() {
		return consumoDezembro;
	}
	public void setConsumoDezembro(BigDecimal consumoDezembro) {
		this.consumoDezembro = consumoDezembro;
	}
	private void calcularConsumoEnergia() {
		if(statusDispositivo) {
			ZonedDateTime agora = ZonedDateTime.now(FUSO_BR);
			long horasDecorridas = Duration.between(dataUltimoCalculo, agora).toHours();
			setConsumoTotal(consumoPorHora.multiply(BigDecimal.valueOf(horasDecorridas)));
			dataUltimoCalculo = agora;
		} else {
			System.out.println("O dispositivo está desligado");
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
