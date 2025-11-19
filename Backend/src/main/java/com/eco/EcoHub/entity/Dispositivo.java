package com.eco.EcoHub.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Dispositivos")
public class Dispositivo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idDispositivo;
	
	@NotBlank(message = "O nome do dispositivo é obrigatório")
	private String nomeDispositivo;

	private String tipoDispositivo;
	
	@NotBlank(message = "O consumo energético é obrigatório")
	private BigDecimal consumoEnergetico;

	private boolean statusDispositivo;

	public UUID getIdDispositivo() {
		return this.idDispositivo;
	}

	public void setIdDispositivo(UUID idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public String getNomeDispositivo() {
		return this.nomeDispositivo;
	}

	public void setNomeDispositivo(String nomeDispositivo) {
		this.nomeDispositivo = nomeDispositivo;
	}

	public String getTipoDispositivo() {
		return this.tipoDispositivo;
	}
	
	public BigDecimal getConsumoEnergetico() {
		return consumoEnergetico;
	}
	
	public void setConsumoEnergetico(BigDecimal consumoEnergetico) {
		this.consumoEnergetico = consumoEnergetico;
	}

	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public boolean getStatusDispositivo() {
		return this.statusDispositivo;
	}

	public void setStatusDispositivo(boolean statusDispositivo) {
		this.statusDispositivo = statusDispositivo;
	}

}
