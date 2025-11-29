package com.eco.EcoHub.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comodo")
public class Comodo {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idComodo;
	@Column(name = "nome_comodo")
	private String nomeComodo;
	
	@ManyToMany
	@JoinTable(name = "comodo_dispositivo",
		joinColumns = @JoinColumn(name = "idComodo"),
		inverseJoinColumns = @JoinColumn(name = "idDispositivo")
	)
	private Set<Dispositivo> comodoDispositivos = new HashSet<>();
	
	public String getNomeComodo() {
		return nomeComodo;
	}
	
	public void setNomeComodo(String nomeComodo) {
		this.nomeComodo = nomeComodo;
	}
	
	public Set<Dispositivo> getDispositivos() {
		return comodoDispositivos;
	}
	
	public void setDispositivos(Set<Dispositivo> dispositivos) {
		this.comodoDispositivos = dispositivos;
	}
	

}
