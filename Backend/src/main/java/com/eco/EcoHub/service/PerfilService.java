package com.eco.EcoHub.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eco.EcoHub.entity.Perfil;
import com.eco.EcoHub.entity.User;
import com.eco.EcoHub.perfilDTO.PerfilDTO;
import com.eco.EcoHub.repository.PerfilRepository;
import com.eco.EcoHub.repository.UserRepository;

@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private UserService UserService;
	
	@Autowired
	private UserRepository userRepository;
	
	public Perfil criarPerfil(PerfilDTO perfilDTO, UUID id) {
		User usuario = UserService.encontrarUsuarioPorID(id);
		Perfil perfil = new Perfil();
		perfil.setNome(perfilDTO.getNome());
		perfilRepository.save(perfil);
		usuario.getUsuarioPerfis().add(perfil);
		perfil.getUsuario().add(usuario);
		userRepository.save(usuario);
		return perfilRepository.save(perfil);
	}

	public Perfil listarPerfilPorId(UUID id) {
		return perfilRepository.findByIdPerfil(id)
	            .orElseThrow(() -> new RuntimeException("Perfil não encontrado com o ID: " + id));
	}
	
	public Iterable<Perfil> listarTodosPerfis() {
		return perfilRepository.findAll();
	}

	public Perfil atualizarPerfil(UUID id, PerfilDTO perfilDTO) {
		Perfil perfilExistente = perfilRepository.findByIdPerfil(id)
	            .orElseThrow(() -> new RuntimeException("Perfil não encontrado com o ID: " + id));
		perfilExistente.setNome(perfilDTO.getNome());
		return perfilRepository.save(perfilExistente);
	}

	public void deletarPerfil(UUID id) {
		Perfil perfil = perfilRepository.findByIdPerfil(id)
	            .orElseThrow(() -> new RuntimeException("Perfil não encontrado com o ID: " + id));
	    perfilRepository.delete(perfil);
	}

}
