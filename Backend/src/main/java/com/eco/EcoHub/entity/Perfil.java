package com.eco.EcoHub.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idPerfil;
	
	@Column(name = "nome")
	private String nome;
	
	@ManyToMany(mappedBy = "usuarioPerfis")
	private Set<User> usuario = new HashSet<>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Set<User> getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Set<User> usuario) {
		this.usuario = usuario;
	}

}
